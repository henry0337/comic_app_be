package com.henry.demo.dto;

import com.henry.demo.models.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
