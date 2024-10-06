package com.henry.demo.common;

import com.henry.demo.domain.model.User;
import com.henry.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.henry.demo.domain.model.Role.*;

/**
 * Lớp này sẽ chịu trách nhiệm tải trước một vài cấu hình cần thiết.
 */
@Configuration
@RequiredArgsConstructor
public class Preloader {

    private final UserRepository userRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            final List<User> currentListOfAdminAccount = userRepository.findByRole(ADMIN);
            final List<User> currentListOfUserAccount = userRepository.findByRole(USER);
            final List<User> currentListOfModeratorAccount = userRepository.findByRole(MODERATOR);

            if (currentListOfAdminAccount.isEmpty()) {
                User admin = User.builder()
                        .name("Administrator")
                        .email("admin@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("admin"))
                        .role(ADMIN)
                        .build();

                userRepository.save(admin);
            }

            if (currentListOfUserAccount.isEmpty()) {
                User localUser = User.builder()
                        .name("Local user")
                        .email("user@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("user"))
                        .role(USER)
                        .build();

                userRepository.save(localUser);
            }

            if (currentListOfModeratorAccount.isEmpty()) {
                User moderator = User.builder()
                        .name("Moderator 1")
                        .email("moderator@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("moderator"))
                        .role(MODERATOR)
                        .build();

                userRepository.save(moderator);
            }
        };
    }
}
