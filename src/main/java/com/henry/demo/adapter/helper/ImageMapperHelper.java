package com.henry.demo.adapter.helper;

import com.henry.demo.domain.model.Episode;
import com.henry.demo.usecase.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMapperHelper {

    private final EpisodeService service;

    public Episode map(int episodeId) {
        return service.getById(episodeId);
    }
}
