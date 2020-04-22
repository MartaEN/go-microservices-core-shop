package com.marta.sandbox.microservices.shop.web.rest;

import com.marta.sandbox.microservices.shop.model.ProductViewDto;
import com.marta.sandbox.microservices.shop.service.api.ProductService;
import com.marta.sandbox.microservices.shop.model.ProductCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    private ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductViewDto> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductViewDto>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductViewDto> createProduct(@RequestBody ProductCreateDto productCreateDto) {
       return new ResponseEntity<>(productService.create(productCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductViewDto> editProduct(@RequestBody ProductViewDto productViewDto) {
        return new ResponseEntity<>(productService.update(productViewDto), HttpStatus.OK);
    }
}
