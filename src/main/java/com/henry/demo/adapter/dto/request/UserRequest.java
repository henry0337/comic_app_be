package com.henry.demo.adapter.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String role;
    private short canAuthenticate;
}
