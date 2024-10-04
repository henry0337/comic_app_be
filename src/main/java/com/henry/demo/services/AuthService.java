package com.henry.demo.services;

import com.henry.demo.holders.requests.LoginRequest;
import com.henry.demo.holders.responses.AuthResponse;
import com.henry.demo.holders.requests.ChangePasswordRequest;
import com.henry.demo.holders.requests.RegisterRequest;
import com.henry.demo.exceptions.SameAsOldPasswordException;
import com.henry.demo.models.User;
import com.henry.demo.repositories.UserRepository;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtService jwtService;
    private final JavaMailSender mailSender;

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
        int requestCode = obtainRequestCodeAfterSentMail(email);

        if (user == null || requestCode != body.getRequestCode() || !Objects.equals(body.getRepeatedPassword(), body.getNewPassword()))
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

    private int obtainRequestCodeAfterSentMail(String email) {
        int requestCode = new Random().nextInt(1000000);
        final SimpleMailMessage message = new SimpleMailMessage();

        while (requestCode >= 100000) {
            message.setFrom("hung03072003@gmail.com");
            message.setTo(email);
            message.setSubject("Yêu cầu thay đổi mật khẩu");
            message.setText("Hãy sử dụng mã sau để xác thực yêu cầu trên: " + requestCode);
            message.setSentDate(new Date(System.currentTimeMillis()));
            try {
                mailSender.send(message);
            } catch (RuntimeException e) {
                Sentry.captureException(e);
                Sentry.captureMessage(e.getLocalizedMessage());
                Sentry.captureEvent(new SentryEvent(e));
            }
        }
        return requestCode;
    }
}
