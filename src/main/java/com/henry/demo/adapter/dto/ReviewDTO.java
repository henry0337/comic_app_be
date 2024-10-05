package com.henry.demo.adapter.dto;

import com.henry.demo.domain.model.Comic;
import com.henry.demo.domain.model.User;
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
