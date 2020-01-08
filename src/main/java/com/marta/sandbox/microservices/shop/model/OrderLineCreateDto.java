package com.marta.sandbox.microservices.shop.model;

import lombok.Value;

@Value
public class OrderLineCreateDto {
    private String productId;
    private int quantity;
}
