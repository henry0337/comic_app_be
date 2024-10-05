package com.henry.demo.adapter.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * Thông báo cho Spring biết rằng chỉ có 2 vai trò là {@code ROLE_ADMIN} hoặc {@code ROLE_MODERATOR}
 * mới có thể sử dụng lớp hoặc phương thức nào đó được đánh dấu bằng chú thích này.
 *
 * @see PreAuthorize @PreAuthorize
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasRole('ADMIN') || hasRole('MODERATOR')")
public @interface OnlyAdminAndModerator {}
