package com.marta.sandbox.microservices.shop.web.rest;

import com.marta.sandbox.microservices.shop.model.OrderActions;
import com.marta.sandbox.microservices.shop.model.OrderLineCreateDto;
import com.marta.sandbox.microservices.shop.model.OrderViewDto;
import com.marta.sandbox.microservices.shop.persistence.domain.User;
import com.marta.sandbox.microservices.shop.service.api.OrderService;
import com.marta.sandbox.microservices.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderResource(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<OrderViewDto> placeOrder(@RequestBody List<OrderLineCreateDto> orderLines) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        OrderViewDto order = orderService.placeOrder(user, orderLines);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderViewDto> getOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderViewDto> processOrder(@PathVariable String id, @RequestBody OrderActions action) {
        return ResponseEntity.ok(orderService.processOrder(id, action));
    }

}
