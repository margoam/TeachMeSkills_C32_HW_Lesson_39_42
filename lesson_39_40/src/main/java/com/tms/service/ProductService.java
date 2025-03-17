package com.tms.service;

import com.tms.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long id);
    Optional<Product> createProduct(Product product);
    Optional<Product> updateProduct(Product product);
    Optional<Product> deleteProduct(Long id);
}
