package com.my.model;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        products.add(new Product(1L, "beans", 130));
        products.add(new Product(2L, "carrot", 110));
        products.add(new Product(3L, "beat", 85));
        products.add(new Product(4L, "apple", 105));
        products.add(new Product(5L, "bread", 60));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getProductById(Long id) {
       return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

}
