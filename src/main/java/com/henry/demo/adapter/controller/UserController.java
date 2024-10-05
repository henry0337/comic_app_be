package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.annotation.OnlyAdminAndModerator;
import com.henry.demo.adapter.dto.UserDTO;
import com.henry.demo.adapter.dto.request.UserRequest;
import com.henry.demo.domain.model.User;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.USER)
@CrossOrigin("*")
@RequiredArgsConstructor
@OnlyAdminAndModerator
@Tag(name = "User (Người dùng)")
public class UserController {

    private final UserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(Endpoint.WITH_EMAIL_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User insert(@RequestBody UserRequest request) {
        return service.insert(request);
    }

    @PutMapping(Endpoint.WITH_EMAIL_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable String email, @RequestBody User request) {
        return service.update(email, request);
    }

    @DeleteMapping(Endpoint.WITH_EMAIL_AS_PARAM)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String email) {
        service.delete(email);
    }
}
