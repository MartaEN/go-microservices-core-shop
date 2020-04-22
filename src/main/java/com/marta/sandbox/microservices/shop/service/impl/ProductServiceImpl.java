package com.marta.sandbox.microservices.shop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marta.sandbox.microservices.shop.model.ProductViewDto;
import com.marta.sandbox.microservices.shop.persistence.domain.Product;
import com.marta.sandbox.microservices.shop.persistence.repository.ProductRepository;
import com.marta.sandbox.microservices.shop.service.api.ProductService;
import com.marta.sandbox.microservices.shop.model.ProductCreateDto;
import com.marta.sandbox.microservices.shop.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductViewDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).get()
                .map(product -> MAPPER.convertValue(product, ProductViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductViewDto findById(String id) {
        return MAPPER.convertValue(
                productRepository.findById(id).orElseThrow(ResourceNotFoundException::new),
                ProductViewDto.class);
    }

    @Override
    @Transactional
    public ProductViewDto create(ProductCreateDto productCreateDto) {
        return MAPPER.convertValue(
                productRepository.save(MAPPER.convertValue(productCreateDto, Product.class)),
                ProductViewDto.class);
    }

    @Override
    @Transactional
    public ProductViewDto update(ProductViewDto productViewDto) {
        return MAPPER.convertValue(
                productRepository.save(MAPPER.convertValue(productViewDto, Product.class)),
                ProductViewDto.class);
    }
}
