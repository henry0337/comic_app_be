package com.henry.demo.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String role;
    private short canAuthenticate;
}
