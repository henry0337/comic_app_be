package com.henry.demo.adapter.annotation;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.*;

/**
 * Chú thích rằng chỉ cho phép những ai có vai trò là {@code ROLE_ADMIN} hoặc {@code ROLE_MODERATOR}
 * mới có thể sử dụng các lớp và phương thức được đánh dấu bằng chú thích này.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
public @interface OnlyAdminAndModerator {}
