package com.henry.demo.controllers;

import com.henry.demo.dto.AuthResponse;
import com.henry.demo.dto.ChangePasswordRequest;
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

    @GetMapping("/userInfo")
    @ResponseStatus(HttpStatus.OK)
    public User obtainUserInfoUsingJwtToken(@RequestHeader("Authorization") @NonNull String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return jwtService.obtainUserFromToken(token);
    }

    @PostMapping("/changePassword")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse changePassword(@RequestBody ChangePasswordRequest request) {
        return authService.changePassword(request);
    }
}
