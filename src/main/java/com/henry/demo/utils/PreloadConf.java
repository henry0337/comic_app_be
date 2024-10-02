package com.henry.demo.utils;

import com.henry.demo.models.User;
import com.henry.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.henry.demo.models.Role.ADMIN;
import static com.henry.demo.models.Role.USER;

/**
 * Như tên lớp đã đề cập, lớp này sẽ chịu trách nhiệm tải trước một vài cấu hình cần thiết.
 * Lớp này sẽ không được sử dụng trực tiếp trên mã nguồn mà sẽ do <b>Spring container</b> quản lý.<br>
 * (vd. Thêm tài khoản mặc định, cấu hình, vv.)
 */
@Configuration
@RequiredArgsConstructor
public class PreloadConf {

    private final UserRepository userRepository;

    @Bean
    CommandLineRunner preloadDataRunner() {
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
                User local = User.builder()
                        .name("Anonymous user")
                        .email("user@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("anonymous"))
                        .role(USER)
                        .build();

                userRepository.save(local);
            }
        };
    }
}
