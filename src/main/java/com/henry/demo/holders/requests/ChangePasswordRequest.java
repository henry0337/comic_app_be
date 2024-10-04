package com.henry.demo.holders.requests;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String email;
    private int requestCode;
    private String newPassword;
    private String repeatedPassword;
}
