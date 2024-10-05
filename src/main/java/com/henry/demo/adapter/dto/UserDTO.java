package com.henry.demo.adapter.dto;

import com.henry.demo.domain.model.Review;
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
