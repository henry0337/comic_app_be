package com.henry.demo.controllers;

import com.henry.demo.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actors")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService service;

    // TODO: Triển khai controller.
}
