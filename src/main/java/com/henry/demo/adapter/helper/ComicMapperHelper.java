package com.henry.demo.adapter.helper;

import com.henry.demo.domain.model.Comic;
import com.henry.demo.domain.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComicMapperHelper {

    private final ComicRepository repository;

    public Comic map(int comicId) {
        return repository.findById(comicId).orElseThrow();
    }
}
