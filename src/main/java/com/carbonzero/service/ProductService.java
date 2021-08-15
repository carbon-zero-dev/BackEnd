package com.carbonzero.service;

import java.util.List;

import com.carbonzero.domain.Product;
import com.carbonzero.dto.ProductRequestData;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
