package com.henry.demo.services;

import com.henry.demo.dto.LoginRequest;
import com.henry.demo.dto.AuthResponse;
import com.henry.demo.dto.ChangePasswordRequest;
import com.henry.demo.dto.RegisterRequest;
import com.henry.demo.models.User;
import com.henry.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
    public AuthResponse changePassword(@NonNull ChangePasswordRequest body) {
        String email = jwtService.extractUsername(body.getToken());
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && jwtService.isTokenValid(body.getToken(), user)) {
            if (!encoder.matches(body.getOldPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Mật khẩu cũ không đúng.");
            }

            user.setPassword(encoder.encode(body.getNewPassword()));
            userRepository.save(user);

            String jwt = jwtService.generateTokenWithUserInfo(user);

            return new AuthResponse(jwt, body.getToken());
        }
        return null;
    }


}
