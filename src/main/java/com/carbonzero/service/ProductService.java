package com.carbonzero.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.carbonzero.domain.Product;

public interface ProductService {

    List<Product> getProducts(PageRequest pageRequest);

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product source);

    void deleteProduct(Long id);
}
