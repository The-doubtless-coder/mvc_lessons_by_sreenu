package org.rest.incorporated.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

public class ProductRequest {

    private String productId;
    @NotBlank(message = "product code cannot be null or empty")
    private String productCode;
    @NotBlank(message = "product name cannot be null or empty")
    private String name;
    @Positive(message = "quantity must be a positive value")
    @Min(value = 1, message = "quantity must be more than zero")
    private int quantity;
    @Min(value = 1, message = "price cannot be below zero")
    @Positive(message = "product price must be a positive value")
    private float price;
    @NotBlank(message = "product imageUrl cannot be null or empty")
    private String imageUrl;
    @NotBlank(message = "product description cannot be null or empty")
    private String description;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
