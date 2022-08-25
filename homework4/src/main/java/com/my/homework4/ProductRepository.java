package com.my.homework4;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.insert(new Product("Apple"));
        this.insert(new Product("Chicken"));
        this.insert(new Product("Milk"));
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(productMap.values());
    }

    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    public Product saveProduct(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
        return product;
    }

    public void deleteProducts() {
        productMap.clear();
    }

    public void deleteProductById(Long id) {
        productMap.remove(id);
    }

}
