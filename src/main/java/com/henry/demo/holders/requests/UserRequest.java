package com.henry.demo.holders.requests;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String role;
    private short canAuthenticate;
}
