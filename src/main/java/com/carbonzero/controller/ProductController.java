package com.carbonzero.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carbonzero.domain.Product;
import com.carbonzero.dto.ProductRequestData;
import com.carbonzero.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductRequestData productRequestData) {
        Product savedProduct = productServiceImpl.createProduct(productRequestData);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedProduct.getId())
            .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity
            .created(location)
            .body(savedProduct);
    }
}
