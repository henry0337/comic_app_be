package com.henry.demo.dto;

import com.henry.demo.models.Comic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeDTO {
    private String title;
    private short status;
    private Date publishedAt;
    private Comic comic;
}
