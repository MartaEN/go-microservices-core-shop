package com.marta.sandbox.microservices.shop.persistence.repository;

import com.marta.sandbox.microservices.shop.persistence.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
}
