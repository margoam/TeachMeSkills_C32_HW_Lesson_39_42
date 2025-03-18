package com.tms.service;

import com.tms.model.Product;
import com.tms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Product product) {
        Boolean result = productRepository.update(product);
        if (result) {
            return getProductById(product.getId());
        }
        return Optional.empty();
    }

    public Optional<Product> deleteProduct(Long id) {
        Boolean result = productRepository.deleteById(id);
        if (result) {
            return getProductById(id);
        }
        return Optional.empty();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
