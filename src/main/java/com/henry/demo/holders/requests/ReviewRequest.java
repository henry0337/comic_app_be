package com.henry.demo.holders.requests;

import lombok.Data;

@Data
public class ReviewRequest {
    private String comment;
    private int rating;
    private int userId;
    private int comicId;
}
