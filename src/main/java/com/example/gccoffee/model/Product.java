package com.example.gccoffee.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private String productName;
    private Category category; //변경가능하게 만듬
    private long price;
    private String description;
    private final LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Product(UUID productId, String productName, Category category, long price){
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.createAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updateAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public Product(UUID productId, String productName, Category category, long price, String description, LocalDateTime createAt, LocalDateTime updateAt) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        this.updateAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setCategory(Category category) {
        this.category = category;
        this.updateAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setPrice(long price) {
        this.price = price;
        this.updateAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public void setDescription(String description) {
        this.description = description;
        this.updateAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
}
