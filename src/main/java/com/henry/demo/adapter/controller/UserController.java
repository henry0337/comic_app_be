package com.henry.demo.adapter.controller;

import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.USER)
@CrossOrigin("*")
@RequiredArgsConstructor
@PreAuthorize("hasRole(\"ADMIN\")")
public class UserController {

    private final UserService service;
}
