package com.henry.demo.usecase.service;

import com.henry.demo.adapter.dto.request.LoginRequest;
import com.henry.demo.adapter.dto.response.AuthResponse;
import com.henry.demo.adapter.dto.request.ChangePasswordRequest;
import com.henry.demo.adapter.dto.request.RegisterRequest;
import com.henry.demo.common.annotation.Incubating;
import com.henry.demo.domain.model.User;
import com.henry.demo.domain.repository.UserRepository;
import com.henry.demo.usecase.exception.SameAsOldPasswordException;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtService jwtService;

    @NonNull
    public void register(@NonNull RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .role(request.getRole())
                .build();

        userRepository.save(user);
    }

    @NonNull
    public AuthResponse login(@NonNull LoginRequest body) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword()));

        User user = userRepository.findByEmail(body.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String jwt = jwtService.generateTokenWithUserInfo(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        return new AuthResponse(jwt, refreshToken);
    }

    @Nullable
    public Object changePassword(@NonNull ChangePasswordRequest body) {
        String token, refreshToken;
        String email = body.getEmail();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null || !Objects.equals(body.getRepeatedPassword(), body.getNewPassword()))
            return null;

        if (encoder.matches(body.getNewPassword(), user.getPassword())) {
            Sentry.captureException(new SameAsOldPasswordException("Trùng mật khẩu cũ"));
            Sentry.captureMessage(
                    String.format("""
                        Mật khẩu của người dùng "%s" đã được tìm thấy trước khi bị đổi sang mật khẩu mới \n
                        nhờ vào backend "hơi" tân tiến của tôi :>
                        """, user.getUsername()
                    )
            );
            return null;
        }

        user.setPassword(encoder.encode(body.getNewPassword()));
        userRepository.save(user);

        token = jwtService.generateTokenWithUserInfo(user);
        refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        return new AuthResponse(token, refreshToken);
    }
}
