package com.carbonzero.service;

import java.util.List;

import com.carbonzero.domain.Product;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct();

    Product deleteProduct(Long id);
}
