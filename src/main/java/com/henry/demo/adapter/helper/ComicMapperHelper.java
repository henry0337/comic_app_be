package com.henry.demo.adapter.helper;

import com.henry.demo.domain.model.Comic;
import com.henry.demo.usecase.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComicMapperHelper {

    private final ComicService service;

    public Comic map(int comicId) {
        return service.getById(comicId);
    }
}
