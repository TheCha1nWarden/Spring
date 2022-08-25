package com.my.homework4;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class Product {
    private static Long currentId = 1l;

    private Long id;

    @NotBlank(message = "can`t be empty!")
    private String title;

    @Min(0)
    private int cost;

    @Min(0)
    private int weight;

    public Product() {
    }

    public Product(String title) {
        this.title = title;
    }

    public void setCurrentId() {
        this.id = currentId++;
    }

}
