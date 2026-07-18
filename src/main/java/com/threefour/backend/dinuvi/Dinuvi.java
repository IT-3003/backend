package com.threefour.backend.dinuvi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Dinuvi {

    @Id
    private Long itemId;

    private String itemName;

    private String category;

    private double baseprice;

    public Dinuvi() {
    }

    public Dinuvi(Long itemId, String itemName, String category, double baseprice ) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.baseprice = baseprice;
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


    public double getBaseprice() {
        return baseprice;
    }   

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
