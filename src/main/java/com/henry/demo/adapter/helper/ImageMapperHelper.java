package com.henry.demo.adapter.helper;

import com.henry.demo.domain.model.Episode;
import com.henry.demo.domain.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMapperHelper {

    private final EpisodeRepository repository;

    public Episode map(int episodeId) {
        return repository.findById(episodeId).orElseThrow();
    }
}
