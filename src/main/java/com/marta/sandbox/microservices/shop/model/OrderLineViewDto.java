package com.marta.sandbox.microservices.shop.model;

import lombok.Value;

@Value
public class OrderLineViewDto {
    private String productId;
    private String productDescription;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
}
