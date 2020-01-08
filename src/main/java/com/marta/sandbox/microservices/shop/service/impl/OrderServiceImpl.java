package com.marta.sandbox.microservices.shop.service.impl;

import com.marta.sandbox.microservices.shop.model.OrderActions;
import com.marta.sandbox.microservices.shop.model.OrderLineCreateDto;
import com.marta.sandbox.microservices.shop.model.OrderViewDto;
import com.marta.sandbox.microservices.shop.persistence.domain.Order;
import com.marta.sandbox.microservices.shop.persistence.domain.OrderLine;
import com.marta.sandbox.microservices.shop.persistence.domain.Product;
import com.marta.sandbox.microservices.shop.persistence.domain.User;
import com.marta.sandbox.microservices.shop.persistence.repository.OrderRepository;
import com.marta.sandbox.microservices.shop.persistence.repository.ProductRepository;
import com.marta.sandbox.microservices.shop.service.api.OrderService;
import com.marta.sandbox.microservices.shop.service.exceptions.IllegalActionException;
import com.marta.sandbox.microservices.shop.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public OrderViewDto placeOrder(User user, List<OrderLineCreateDto> orderLines) {
        Order order = new Order();
        order.setUser(user);
        orderLines.forEach(line -> {
            Product product = productRepository.findById(line.getProductId()).orElseThrow(ResourceNotFoundException::new);
            order.addOrderLine(new OrderLine(order, product, line.getQuantity()));
        });
        order.setPlaced(LocalDate.now());
        return new OrderViewDto(orderRepository.save(order));
    }

    @Override
    public OrderViewDto findById(String id) {
//        Order order = orderRepository.findOrderById(id);
//        OrderViewDto orderViewDto = new OrderViewDto(order);
        return new OrderViewDto(orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    @Transactional
    public OrderViewDto processOrder(String id, OrderActions action) {
        Order order = orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        switch (action) {
            case MARK_AS_PAID: return markAsPaid(order);
            case MARK_AS_SHIPPED: return markAsShipped(order);
            default: throw new IllegalActionException("Unprocessable action to order: " + action);
        }
    }

    private OrderViewDto markAsPaid(Order order) {
        if (order.getPaid() == null) {
            order.setPaid(LocalDate.now());
            return new OrderViewDto(order);
        } else {
            throw new IllegalActionException("Order was already paid");
        }
    }

    private OrderViewDto markAsShipped(Order order) {
        if (order.getShipped() == null) {
            order.setShipped(LocalDate.now());
            return new OrderViewDto(order);
        } else {
            throw new IllegalActionException("Order was already shipped");
        }
    }
}
