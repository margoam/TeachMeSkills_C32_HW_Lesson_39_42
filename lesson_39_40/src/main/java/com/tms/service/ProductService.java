package com.tms.service;

import com.tms.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}
