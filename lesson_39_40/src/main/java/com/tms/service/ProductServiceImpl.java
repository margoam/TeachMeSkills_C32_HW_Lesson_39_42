package com.tms.service;

import com.tms.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> createProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteProduct(Long id) {
        return Optional.empty();
    }
}
