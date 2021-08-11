package com.carbonzero.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        List<Product> products = productServiceImpl.getProducts();

        return ResponseEntity
            .ok()
            .body(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> detail(@PathVariable Long id) {
        Product product = productServiceImpl.getProduct(id);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .buildAndExpand(product.getId())
            .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity
            .ok()
            .headers(headers)
            .body(product);
    }
}
