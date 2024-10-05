package com.henry.demo.adapter.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String email;
    private String newPassword;
    private String repeatedPassword;
}