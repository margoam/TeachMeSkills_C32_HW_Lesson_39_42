package com.tms.controller;

import com.tms.model.Product;
import com.tms.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all-products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/create")
    public String getProductCreatePage() { return "createProduct";}

    @GetMapping("/edit/{id}")
    public String getProductUpdatePage(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "Product not found: id=" + id);
            return "innerError";
        }
        model.addAttribute("product", product.get());
        return "editProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") Product product, HttpServletResponse response, Model model) {
        Optional<Product> createdProduct = productService.createProduct(product);
        if (createdProduct.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product not created");
            return "innerError";
        }
        return "redirect:/products/all-products";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpServletResponse response) {
        Optional<Product> productUpdated = productService.updateProduct(product);
        if (productUpdated.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product not updated.");
            return "innerError";
        }
        return "redirect:/products/all-products";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long id, Model model, HttpServletResponse response) {
        Optional<Product> deletedProduct = productService.deleteProduct(id);
        if (deletedProduct.isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Product is not deleted.");
            return "innerError";
        }
        return "redirect:/products/all-products";
    }
}
