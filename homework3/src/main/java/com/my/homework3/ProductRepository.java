package com.my.homework3;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        Collections.addAll(products,
                new Product("Apple"),
                new Product("Chicken"),
                new Product("Milk"));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void addProduct(Product product) {
        product.setCurrentId();
        products.add(product);
    }

    public void deleteProducts() {
        products.clear();
    }

    public void deleteProductById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

}
