package com.marta.sandbox.microservices.shop.web.rest;

import com.marta.sandbox.microservices.shop.model.UserCreateDto;
import com.marta.sandbox.microservices.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody @Valid UserCreateDto user) {
        userService.create(user);
    }
}
