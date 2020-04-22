package com.marta.sandbox.microservices.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marta.sandbox.microservices.shop.persistence.domain.Order;
import lombok.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Value
public class OrderViewDto {

    private String orderId;

    private String userId;

    private String userName;

    private List<OrderLineViewDto> orderLines;

    private Double totalOrderPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate placed;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paid;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate shipped;

    public OrderViewDto(Order order) {
        this.orderId = order.getId();
        this.userId = order.getUser().getId();
        this.userName = order.getUser().getUsername();
        this.orderLines = new ArrayList<>();
        order.getOrderLines().forEach(line ->
                this.orderLines.add(new OrderLineViewDto(line.getProduct().getId(), line.getProduct().getDescription(),
                        line.getQuantity(), line.getPrice(), line.getTotalPrice())));
        this.totalOrderPrice = order.getTotalOrderPrice();
        this.placed = order.getPlaced();
        this.paid = order.getPaid();
        this.shipped = order.getShipped();
    }
}
