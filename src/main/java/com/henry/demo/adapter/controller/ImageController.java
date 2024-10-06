package com.henry.demo.adapter.controller;

import com.henry.demo.infrastructure.config.Endpoint;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.IMAGE)
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Ảnh nhân vật (Image)")
public class ImageController {
}
