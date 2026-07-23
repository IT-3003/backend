package com.threefour.backend.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    private int orderItemId;      // Unique line-item ID
    @Column(name = "order_id", insertable = false, updatable = false)
    private int orderId;          // FK -> parent Order
    private int productId;        // FK -> Item (from Items Management)
    private String productName;   // Snapshot of name at order time
    private int quantity;         // Quantity ordered
    private double unitPrice;     // Snapshot of price at order time (protects against later price changes)
    private double lineTotal;     // quantity * unitPrice

    public OrderItem(int orderItemId, int orderId, int productId, String productName, int quantity, double unitPrice, double lineTotal) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public void setOrder(Order order) {
    }
}


