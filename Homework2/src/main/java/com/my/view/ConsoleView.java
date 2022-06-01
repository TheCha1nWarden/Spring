package com.my.view;

import com.my.model.Product;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String CLEAR_CART = "Successful cleaning!";
    private static final String CHOOSE_PRODUCT = "Choose product id:\n";
    private static final String CHOOSE_OPTION = "Choose option:\n";
    private static final String MAIN_MENU = "Main menu:\n" +
            "1. Show all products\n" +
            "2. Show products in cart\n" +
            "3. Add products to cart\n" +
            "4. Remove product from cart\n" +
            "5. Clear cart\n" +
            "6. Exit\n" +
            CHOOSE_OPTION;
    private static final String BYE = "Bye!";

    public void printMainMenu() {
        System.out.print(MAIN_MENU);
    }

    public void printProducts(List<Product> products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void printProductsWithChoose(List<Product> products) {
        printProducts(products);
        System.out.print(CHOOSE_PRODUCT);
    }

    public void printClearCart() {
        System.out.println(CLEAR_CART);
    }

    public void printBye() {
        System.out.println(BYE);
    }

    public Options readOptionForMainMenu() {
        Options option = null;
        int tmp = SCANNER.nextInt();
        for (Options o : Options.values()) {
            if (o.getNumber() == tmp) {
                option = o;
                break;
            }
        }
        return option;
    }

    public Long readIdProduct() {
        return SCANNER.nextLong();
    }

}
