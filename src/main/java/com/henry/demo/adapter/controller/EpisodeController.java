package com.henry.demo.adapter.controller;

import com.henry.demo.infrastructure.config.Endpoint;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.EPISODE)
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Thể loại (Genre)")
public class EpisodeController {
}
