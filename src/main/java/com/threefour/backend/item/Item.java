package com.threefour.backend.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {

    @Id
    @NotNull(message = "Item ID is required")
    private Long itemId;

    @NotBlank(message = "Item name is required")
    @Size(min = 3, max = 100, message = "Item name must be between 3 and 100 characters")
    private String itemName;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Base price must be greater than 0")
    private double baseprice;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQuantity;

    @Positive(message = "Cost price must be greater than 0")
    private double costPrice;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    public Item() {
    }

    public Item(Long itemId, String itemName, double costPrice, String category,
                String description, String imageUrl, String brand,
                int stockQuantity, double baseprice) {

        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.baseprice = baseprice;
        this.brand = brand;
        this.description = description;
        this.costPrice = costPrice;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    public Long getitemId() {
        return itemId;
    }

    public void setitemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getitemName() {
        return itemName;
    }

    public void setitemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}