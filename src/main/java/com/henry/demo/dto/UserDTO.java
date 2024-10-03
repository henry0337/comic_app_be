package com.henry.demo.dto;

import com.henry.demo.models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String role;
    private short canAuthenticate;
    private List<Review> reviews;
}
