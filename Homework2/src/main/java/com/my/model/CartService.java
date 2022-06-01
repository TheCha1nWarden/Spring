package com.my.model;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private ProductService productService;
    private Cart cart;

    public CartService(ProductService productService, Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCartByProductId(Long productId) {
        Optional<Product> product = productService.findProductById(productId);
        product.ifPresent(value -> cart.add(value));
    }

    public void clearCurrentCart() {
        cart.clear();
    }

    public void removeFromCartById(Long id) {
        cart.removeById(id);
    }
}
