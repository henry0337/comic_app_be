package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.dto.request.ChangePasswordRequest;
import com.henry.demo.adapter.dto.request.LoginRequest;
import com.henry.demo.adapter.dto.request.RegisterRequest;
import com.henry.demo.adapter.dto.response.AuthResponse;
import com.henry.demo.domain.model.User;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.AuthService;
import com.henry.demo.usecase.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.AUTH)
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    /**
     * Tạo tài khoản mới.
     * @param request Thông tin của tài khoản mới này.
     */
    @NonNull
    @PostMapping(Endpoint.SIGNUP)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    /**
     * Hàm dùng để đăng nhập, xác thực tới máy chủ.
     * @param request Thông tin dùng để xác thực.
     * @return Token được mã hóa để lưu thông tin người dùng.
     */
    @PostMapping(Endpoint.LOGIN)
    @ResponseStatus(HttpStatus.OK)
    @NonNull
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    /**
     * Trả về thông tin người dùng được lưu trong JWT token.
     *
     * @param token Token được sử dụng để lấy thông tin chi tiết về người dùng.
     * @return Thông tin của người dùng được lưu trong token.
     * @implNote Nếu như bạn muốn xác thực trên <b>Swagger UI</b>, hãy đảm bảo rằng bạn đang KHÔNG truyền mã xác thực vào biến {@code token},
     * thay vào đó hãy truyền vào phần <b>Bearer Token</b> trên đó (phần này nằm trong nút <b>Authorize</b> ở góc trên bên phải).
     */
    @GetMapping(Endpoint.USER_INFO)
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "Bearer Token") })
    public User obtainUserInfoUsingJwtToken(@RequestHeader("Authorization") @Nullable String token) {
        String obtainedToken = null;

        if (token == null) {
            obtainedToken = extractJwtTokenFromSecurityContext();
        } else if (token.startsWith("Bearer ")) {
            obtainedToken = token.substring(7);
        }

        return jwtService.obtainUserFromToken(obtainedToken);
    }

    /**
     * Gửi yêu cầu thay đổi mật khẩu của người dùng lên máy chủ.
     *
     * @param request
     * @return Token mới sau khi đổi mật khẩu.
     */
    @PostMapping(Endpoint.CHANGE_PASSWORD)
    @ResponseStatus(HttpStatus.OK)
    public Object changePassword(@RequestBody ChangePasswordRequest request) {
        return authService.changePassword(request);
    }

    private String extractJwtTokenFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Phong cách viết dạng "Pattern Matching for instanceof" của Java 14
        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getTokenValue();
        }

        throw new IllegalStateException("Token không tồn tại trong SecurityContext");
    }
}
