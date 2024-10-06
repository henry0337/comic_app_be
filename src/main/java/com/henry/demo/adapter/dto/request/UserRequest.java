package com.henry.demo.adapter.dto.request;

import com.henry.demo.domain.model.Role;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private Role role;
    private String password;
    private short isAccountExpired = 0;
    private short isAccountLocked = 0;
    private short isCredentialsExpired = 0;
    private short canAuthenticate = 1;
}
