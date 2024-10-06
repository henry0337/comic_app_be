package com.henry.demo.infrastructure.config;

import com.henry.demo.domain.model.Role;
import com.henry.demo.usecase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.function.IntFunction;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final UserService userService;

    @Bean
    SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http) throws Exception {
        String[] roles = getAllAvailableRoles();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(route -> route
                        .requestMatchers(
                                Endpoint.AUTH + Endpoint.ALL_INSIDE,
                                Endpoint.OPENAPI_DOCS + Endpoint.ALL_INSIDE,
                                Endpoint.SWAGGER_UI + Endpoint.ALL_INSIDE,
                                Endpoint.ACTOR + Endpoint.ALL_INSIDE,
                                Endpoint.COMIC + Endpoint.ALL_INSIDE,
                                Endpoint.EPISODE + Endpoint.ALL_INSIDE,
                                Endpoint.GENRE + Endpoint.ALL_INSIDE,
                                Endpoint.IMAGE + Endpoint.ALL_INSIDE,
                                Endpoint.REVIEW + Endpoint.ALL_INSIDE
                        ).permitAll()
                        .requestMatchers(
                                Endpoint.AUTH + Endpoint.USER_INFO,
                                Endpoint.AUTH + Endpoint.CHANGE_PASSWORD
                        ).hasAnyRole(roles[0], roles[1])
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(@NonNull AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @NonNull
    private String[] getAllAvailableRoles() {
        return Arrays.stream(Role.values())
                .map(Enum::toString)
                .toArray(String[]::new);
    }
}
