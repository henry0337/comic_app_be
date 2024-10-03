package com.henry.demo.dto;

import com.henry.demo.models.Comic;
import com.henry.demo.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private String comment;
    private int rating;
    private User user;
    private Comic comic;
}
