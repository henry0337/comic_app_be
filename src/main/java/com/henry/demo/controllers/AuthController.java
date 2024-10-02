package com.henry.demo.controllers;

import com.henry.demo.dto.AuthResponse;
import com.henry.demo.dto.LoginRequest;
import com.henry.demo.dto.RegisterRequest;
import com.henry.demo.models.User;
import com.henry.demo.services.AuthService;
import com.henry.demo.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @NonNull
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    @NonNull
    public final ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<AuthResponse> renew(@RequestBody RefreshTokenRequest request) {
//        return ResponseEntity.ok(authService.renewToken(request));
//    }

    @GetMapping("/userInfo")
    @ResponseStatus(HttpStatus.OK)
    public User obtainUserInfoUsingJwtToken(@RequestHeader("Authorization") @NonNull String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Do không dùng DTO nên trường `password` khi trả về sẽ luôn có giá trị `null`, đơn giản là vì vấn đề bảo mật.
        return jwtService.obtainUserFromToken(token);
    }
}
