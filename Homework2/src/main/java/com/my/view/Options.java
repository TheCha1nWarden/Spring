package com.my.view;

public enum Options {
    SHOW_ALL_PRODUCTS(1),
    SHOW_PRODUCTS_IN_CART(2),
    ADD_PRODUCT_TO_CART(3),
    REMOVE_PRODUCT_FROM_CART(4),
    CLEAR_CART(5),
    EXIT(6);

    private final int number;

    Options(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
