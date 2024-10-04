package com.henry.demo.dto;

import com.henry.demo.models.Episode;
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
