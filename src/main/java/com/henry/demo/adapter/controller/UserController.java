package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.annotation.OnlyAdminAndModerator;
import com.henry.demo.adapter.dto.UserDTO;
import com.henry.demo.adapter.dto.request.UserRequest;
import com.henry.demo.domain.model.User;
import com.henry.demo.infrastructure.config.Endpoint;
import com.henry.demo.usecase.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.USER)
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Người dùng (User)")
@OnlyAdminAndModerator
public class UserController {

    private final UserService service;

    @Operation(security = { @SecurityRequirement(name = "Bearer Token") })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    @Operation(security = { @SecurityRequirement(name = "Bearer Token") })
    @GetMapping(Endpoint.WITH_EMAIL_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @Operation(security = { @SecurityRequirement(name = "Bearer Token") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User insert(@RequestBody UserRequest request) {
        return service.insert(request);
    }

    @Operation(security = { @SecurityRequirement(name = "Bearer Token") })
    @PutMapping(Endpoint.WITH_EMAIL_AS_PARAM)
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable String email, @RequestBody UserRequest request) {
        return service.update(email, request);
    }

    @Operation(security = { @SecurityRequirement(name = "Bearer Token") })
    @DeleteMapping(Endpoint.WITH_EMAIL_AS_PARAM)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String email) {
        service.delete(email);
    }
}
