package com.henry.demo.adapter.dto.request;

import com.henry.demo.domain.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role = Role.USER;
}
