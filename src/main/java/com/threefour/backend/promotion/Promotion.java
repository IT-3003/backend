package com.threefour.backend.promotion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;

@Table(name = "promotion")
@Entity
public class Promotion {
    @Id

    @Positive(message = "Promotion ID must be greater than 0")
    private int promotionId;

    @NotBlank(message = "Promotion name is required")
    @Size(min = 3, max = 50, message = "Promotion name must be between 3 and 50 characters")
    private String promotionName;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @NotNull(message = "Discount value is required")
    @Positive(message = "Discount value must be greater than 0")
    private Double discountValue;

    @NotBlank(message = "Discount type is required")
    private String discountType;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Positive(message = "Item ID must be greater than 0")
    private int itemId;

    public Promotion(int promotionId, String promotionName,
                     String description, Double discountValue, String discountType,
                     LocalDate startDate, LocalDate endDate, int itemId) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.description = description;
        this.discountValue = discountValue;
        this.discountType = discountType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemId = itemId;
    }

    public Promotion(){

    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
