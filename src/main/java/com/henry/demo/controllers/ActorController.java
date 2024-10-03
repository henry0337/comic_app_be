package com.henry.demo.controllers;

import com.henry.demo.models.Actor;
import com.henry.demo.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Actor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Actor getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor insert(@RequestBody Actor actor) {
        return service.insert(actor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Actor update(@PathVariable int id, @RequestBody Actor actor) {
        return service.update(id, actor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
