package com.my.homework3;

public class Product {

    private static Long currentId = 1l;

    private Long id;
    private String title;

    public Product() {
    }

    public Product(String title) {
        this.id = currentId++;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentId() {
        this.id = currentId++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
