package com.henry.demo.common;

import com.henry.demo.domain.model.User;
import com.henry.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.henry.demo.domain.model.Role.ADMIN;
import static com.henry.demo.domain.model.Role.USER;

/**
 * Lớp này sẽ chịu trách nhiệm tải trước một vài cấu hình cần thiết.
 */
@Configuration
@RequiredArgsConstructor
public class Preloader {

    private final UserRepository userRepository;

    /**
     * Khởi tạo cơ sở dữ liệu mới.
     * @param jdbcTemplate
     * @return Đối tượng {@link CommandLineRunner} thực hiện khởi tạo cơ sở dữ liệu mới.
     */
    @Bean
    CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS comic_app");
    }

    @Bean
    CommandLineRunner initData() {
        return args -> {
            final List<User> currentListOfAdminAccount = userRepository.findByRole(ADMIN);
            final List<User> currentListOfUserAccount = userRepository.findByRole(USER);

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
        };
    }
}
