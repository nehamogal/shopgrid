package com.shopgrid.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequest {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    public ProductRequest() {}
    
    public ProductRequest(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}