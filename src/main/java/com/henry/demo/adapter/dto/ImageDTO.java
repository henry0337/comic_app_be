package com.henry.demo.adapter.dto;

import com.henry.demo.domain.model.Episode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private String name;
    private String url;
    private Episode episode;
}
