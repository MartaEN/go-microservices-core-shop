package com.marta.sandbox.microservices.shop.service.api;

import com.marta.sandbox.microservices.shop.model.OrderActions;
import com.marta.sandbox.microservices.shop.model.OrderLineCreateDto;
import com.marta.sandbox.microservices.shop.model.OrderViewDto;
import com.marta.sandbox.microservices.shop.persistence.domain.User;

import java.util.List;

public interface OrderService {

    OrderViewDto placeOrder(User user, List<OrderLineCreateDto> orderLines);

    OrderViewDto findById(String id);

    OrderViewDto processOrder(String id, OrderActions action);
}
