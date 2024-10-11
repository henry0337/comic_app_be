package com.henry.demo.adapter.annotation;

import com.henry.demo.infrastructure.config.SecurityConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.lang.annotation.*;

/**
 * Chú thích rằng chỉ cho phép những ai có vai trò là {@code ROLE_ADMIN} hoặc {@code ROLE_MODERATOR}
 * mới có thể sử dụng các lớp và phương thức được đánh dấu bằng chú thích này.
 *
 * @apiNote Chú thích này sẽ bị mất tác dụng ngay lập tức nếu như bạn đang phân quyền cho các route bằng phương thức {@code authorizeHttpRequests()}
 * của lớp {@link HttpSecurity}. Kiểm tra lớp {@link SecurityConfiguration} của dự án này để biết rõ hơn.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
public @interface OnlyAdminAndModerator {}
