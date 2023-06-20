package com.example.springjpa.dtos;

import com.example.springjpa.model.Product;
import com.sun.istack.NotNull;

public class ProductDto {
    // in creating it can be optional
    // for update we need id
    private Integer id;
    private @NotNull String name;
    private @NotNull String imageUrl;
    private @NotNull Double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;

    public ProductDto() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
