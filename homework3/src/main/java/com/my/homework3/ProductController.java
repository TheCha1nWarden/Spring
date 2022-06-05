package com.my.homework3;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/v1/products")
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @GetMapping("/api/v1/products/{id}")
    public Product getProductById(@PathVariable Long id) {
       return productRepository.getProductById(id);
    }

    @PostMapping("/api/v1/products")
    public void addProduct(@RequestBody Product product) {
        productRepository.addProduct(product);
    }

    @DeleteMapping("/api/v1/products")
    public void deleteProducts() {
        productRepository.deleteProducts();
    }

    @DeleteMapping("/api/v1/products/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteProductById(id);
    }
}
