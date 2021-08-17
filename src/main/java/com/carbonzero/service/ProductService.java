package com.carbonzero.service;

import com.carbonzero.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product source);

    void deleteProduct(Long id);
}
