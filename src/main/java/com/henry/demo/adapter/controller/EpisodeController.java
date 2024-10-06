package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.dto.EpisodeDTO;
import com.henry.demo.adapter.dto.request.EpisodeRequest;
import com.henry.demo.domain.model.Episode;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.EpisodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.EPISODE)
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Phần (Episode)")
public class EpisodeController {

    private final EpisodeService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EpisodeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public EpisodeDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Episode insert(@RequestBody EpisodeRequest request) {
        return service.insert(request);
    }

    @PutMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public Episode insert(@PathVariable int id, @RequestBody EpisodeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
