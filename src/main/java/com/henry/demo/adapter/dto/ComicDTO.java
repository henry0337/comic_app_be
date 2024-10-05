package com.henry.demo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicDTO {
    private String title;
    private String description;
    private String poster;
    private Date releaseDate;
    private int view;
    private int rating;
    private String type;
    private short status;
    private Date publishedAt;
    private List<String> review;
}
