package com.my.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Scope("prototype")
public class Cart {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void add(Product product) {
        products.add(product);
    }

    public void removeById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public void clear() {
        products.clear();
    }

}
