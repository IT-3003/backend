package com.threefour.backend.Payment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    private int paymentId;

    private int orderId;
    private int userId;
    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String transaction;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;

    private boolean isActive;


    // Required empty constructor for JPA
    public Payment() {

    }


    // Parameterized constructor
    public Payment(int paymentId,
                   int orderId,
                   int userId,
                   double amount,
                   PaymentMethod paymentMethod,
                   String transaction,
                   PaymentStatus paymentStatus,
                   LocalDateTime paymentDate,
                   boolean isActive) {

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transaction = transaction;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.isActive = isActive;
    }


    // Getters

    public int getPaymentId() {
        return paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransaction() {
        return transaction;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public boolean isActive() {
        return isActive;
    }


    // Setters

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}