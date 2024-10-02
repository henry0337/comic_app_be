package com.henry.demo.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String token;
    private String oldPassword;
    private String newPassword;
}
