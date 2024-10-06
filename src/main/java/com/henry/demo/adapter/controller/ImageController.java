package com.henry.demo.adapter.controller;


import com.henry.demo.adapter.dto.ImageDTO;
import com.henry.demo.adapter.dto.request.ImageRequest;
import com.henry.demo.domain.model.Image;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.IMAGE)
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Ảnh nhân vật (Image)")
public class ImageController {

    private final ImageService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public ImageDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Image insert(@RequestBody ImageRequest request) {
        return service.insert(request);
    }

    @PutMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public Image insert(@PathVariable int id, @RequestBody ImageRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping(Endpoint.WITH_ID_AS_PARAM)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
