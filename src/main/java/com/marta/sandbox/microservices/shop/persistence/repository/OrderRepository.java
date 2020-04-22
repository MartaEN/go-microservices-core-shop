package com.marta.sandbox.microservices.shop.persistence.repository;

import com.marta.sandbox.microservices.shop.persistence.domain.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
}
