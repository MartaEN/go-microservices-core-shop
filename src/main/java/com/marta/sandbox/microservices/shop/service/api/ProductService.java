package com.marta.sandbox.microservices.shop.service.api;

import com.marta.sandbox.microservices.shop.model.ProductCreateDto;
import com.marta.sandbox.microservices.shop.model.ProductViewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ProductService {

    List<ProductViewDto> getAllProducts(Pageable pageable);

    ProductViewDto findById(String id);

    ProductViewDto create(ProductCreateDto productCreateDto);

    ProductViewDto update(ProductViewDto productCreateDto);
}
