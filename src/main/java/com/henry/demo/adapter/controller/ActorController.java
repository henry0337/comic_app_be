package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.dto.ActorDTO;
import com.henry.demo.domain.model.Actor;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.ACTOR)
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ActorDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public ActorDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor insert(@RequestBody ActorDTO actorDTO) {
        return service.insert(actorDTO);
    }

    @PutMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public Actor update(@PathVariable int id, @RequestBody ActorDTO actorDTO) {
        return service.update(id, actorDTO);
    }

    @DeleteMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
