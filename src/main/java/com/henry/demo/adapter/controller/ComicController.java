package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.dto.ComicDTO;
import com.henry.demo.domain.model.Comic;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.ComicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.COMIC)
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Truyện tranh (Comic)")
public class ComicController {

    private final ComicService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ComicDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public ComicDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comic insert(@RequestBody ComicDTO comicDTO) {
        return service.insert(comicDTO);
    }

    @PutMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public Comic insert(@PathVariable int id, @RequestBody ComicDTO comicDTO) {
        return service.update(id, comicDTO);
    }

    @DeleteMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
