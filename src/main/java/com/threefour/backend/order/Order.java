package com.threefour.backend.order;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private int orderId;

    private int userId;
    private int branchId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private double subtotal;
    private double discountAmount;
    private String couponCode;
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String deliveryAddress;
    private int paymentId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Order() {
    }

    public Order(int orderId, int userId, int branchId, List<OrderItem> orderItems,
                 double subtotal, double discountAmount, String couponCode,
                 double totalAmount, OrderStatus status,
                 String deliveryAddress, int paymentId,
                 Date orderDate, Date updatedDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.branchId = branchId;
        this.orderItems = orderItems;
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.couponCode = couponCode;
        this.totalAmount = totalAmount;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.paymentId = paymentId;
        this.orderDate = orderDate;
        this.updatedDate = updatedDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}