package com.marta.sandbox.microservices.shop.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductCreateDto {

    @NotNull
    @NotEmpty
    private String description;

    @Min(0)
    private double price;
}
