package com.henry.demo.adapter.dto;

import com.henry.demo.domain.model.Comic;
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
