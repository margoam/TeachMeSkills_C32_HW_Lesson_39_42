package com.tms.controller;

import com.tms.model.Product;
import com.tms.service.ProductService;
import com.tms.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
