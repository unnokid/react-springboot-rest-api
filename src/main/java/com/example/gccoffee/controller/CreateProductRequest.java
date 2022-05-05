package com.example.gccoffee.controller;

import com.example.gccoffee.model.Category;

public class CreateProductRequest {
    //DTO 클래스
    private final String name;
    private final Category category;
    private final long price;
    private final String description;

    public CreateProductRequest(String name, Category category, long price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
