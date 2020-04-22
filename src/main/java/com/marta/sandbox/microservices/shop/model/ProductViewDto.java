package com.marta.sandbox.microservices.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductViewDto extends ProductCreateDto {

    @NotNull
    @NotEmpty
    private String id;
}
