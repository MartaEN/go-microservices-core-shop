package com.marta.sandbox.microservices.shop.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;
}
