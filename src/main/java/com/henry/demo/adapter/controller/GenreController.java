package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.dto.GenreDTO;
import com.henry.demo.domain.model.Genre;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.GENRE)
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Thể loại (Genre)")
public class GenreController {

    private final GenreService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre insert(@RequestBody GenreDTO comicDTO) {
        return service.insert(comicDTO);
    }

    @PutMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public Genre insert(@PathVariable int id, @RequestBody GenreDTO comicDTO) {
        return service.update(id, comicDTO);
    }

    @DeleteMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
