package com.threefour.backend.Payment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Payment {
    private final int paymentID;
    @Id
    private int paymentId;
    private int oderId ;
    private int userId ;
    private double amount ;
    private PaymentMethod paymentMethod ;
    private String transaction;
    private PaymentStatus paymentStatus ;
    private LocalDateTime paymentDate ;
    private boolean isActive ;


    public Payment(int paymentId, int oderId, int userId, double amount, PaymentMethod paymentMethod, String transaction, PaymentStatus paymentStatus, LocalDateTime paymentDate, boolean isActive) {
        this.paymentID = paymentId;
        this.oderId = oderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transaction = transaction;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.isActive = isActive;
    }



    public int getPayment() {
        return paymentId;
    }

    public int getOderId() {
        return oderId;
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

    public void setPayment(int payment) {
        this.paymentId = payment;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
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
