package com.henry.demo.adapter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeRequest {
    private String title;
    private short status;
    private Date publishedAt;
    private int comicId;
}
