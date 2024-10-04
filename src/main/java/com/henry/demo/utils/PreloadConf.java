package com.henry.demo.utils;

import com.henry.demo.models.User;
import com.henry.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.henry.demo.models.Role.ADMIN;
import static com.henry.demo.models.Role.USER;

/**
 * Lớp này sẽ chịu trách nhiệm tải trước một vài cấu hình cần thiết. <br>
 * @apiNote Lớp này sẽ không được sử dụng trực tiếp trên mã nguồn (do được đánh dấu là {@link Configuration})
 * mà sẽ do <b>Spring container</b> quản lý.
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

    /**
     * Khởi tạo cơ sở dữ liệu cho dự án chỉ khi chưa tồn tại.
     * @param jdbcTemplate
     * @return Đối tượng {@link CommandLineRunner} thực hiện khởi tạo cơ sở dữ liệu mới.
     */
    @Bean
    CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS comic_app");
    }
}
