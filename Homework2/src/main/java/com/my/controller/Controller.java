package com.my.controller;

import com.my.model.AppConfig;
import com.my.model.CartService;
import com.my.model.ProductService;
import com.my.view.ConsoleView;
import com.my.view.Options;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Controller {

    private final CartService cartService;
    private final ProductService productService;
    private final ConsoleView consoleView;

    public Controller() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        cartService = context.getBean(CartService.class);
        productService = context.getBean(ProductService.class);
        consoleView = new ConsoleView();
        context.close();
    }

    public void start() {

        while (true) {
            consoleView.printMainMenu();
            Options option = consoleView.readOptionForMainMenu();
            switch (option) {
                case SHOW_ALL_PRODUCTS:
                    consoleView.printProducts(productService.getProducts());
                    break;
                case SHOW_PRODUCTS_IN_CART:
                    consoleView.printProducts(cartService.getCurrentCart().getProducts());
                    break;
                case ADD_PRODUCT_TO_CART:
                    consoleView.printProductsWithChoose(productService.getProducts());
                    cartService.addToCartByProductId(consoleView.readIdProduct());
                    break;
                case REMOVE_PRODUCT_FROM_CART:
                    consoleView.printProductsWithChoose(cartService.getCurrentCart().getProducts());
                    cartService.removeFromCartById(consoleView.readIdProduct());
                    break;
                case CLEAR_CART:
                    cartService.clearCurrentCart();
                    consoleView.printClearCart();
                    break;
                case EXIT:
                    consoleView.printBye();
                    return;
                default:
                    throw new IllegalArgumentException("Incorrect option value");
            }
        }
    }

}
