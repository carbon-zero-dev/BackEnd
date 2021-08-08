package com.carbonzero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carbonzero.domain.Product;
import com.carbonzero.dto.ProductRequestData;
import com.carbonzero.repository.ProductRepository;
import com.github.dozermapper.core.Mapper;

@Service
public class ProductServiceImpl implements ProductService {

    private final Mapper mapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(Mapper dozerMapper, ProductRepository productRepository) {
        this.mapper = dozerMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public Product createProduct(ProductRequestData productRequestData) {

        Product product = mapper.map(productRequestData, Product.class);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct() {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product findProduct(Long id) {
        return null;
    }
}
