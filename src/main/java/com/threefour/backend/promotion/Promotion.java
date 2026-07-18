package com.threefour.backend.promotion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
@Table(name = "promotion")
@Entity
public class Promotion {
    @Id
    private int promotionId;
    private String promotionName;
    private String description;
    private Double discountValue;
    private String discountType;
    private LocalDate startDate;
    private LocalDate endDate;
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
