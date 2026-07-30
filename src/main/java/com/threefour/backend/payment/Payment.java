package com.threefour.backend.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // Added for validation constraints

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "payment_id") 
    private int paymentId;


    @NotNull(message = "Order ID is required")
    @Positive(message = "Order ID must be a positive number")
    @Column(name = "order_id")
    private Integer orderId; // Updated to Integer wrapper

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    @Column(name = "user_id")
    private Integer userId; // Updated to Integer wrapper

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    @Column(name = "amount")
    private Double amount; // Updated to Double wrapper

    @NotNull(message = "Payment method is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @NotBlank(message = "Transaction details cannot be blank")
    @Size(min = 5, max = 100, message = "Transaction text length must be between 5 and 100 characters")
    @Column(name = "transaction")
    private String transaction;

    @NotNull(message = "Payment status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "is_active")
    private boolean isActive;

    // Required empty constructor for JPA
    public Payment() {
    }

    // Parameterized constructor
    public Payment(int paymentId,
                   Integer orderId,
                   Integer userId,
                   Double amount,
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
    public int getPaymentId() { return paymentId; }
    public Integer getOrderId() { return orderId; }
    public Integer getUserId() { return userId; }
    public Double getAmount() { return amount; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public String getTransaction() { return transaction; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public boolean isActive() { return isActive; }

    // Setters
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setTransaction(String transaction) { this.transaction = transaction; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public void setActive(boolean active) { isActive = active; }
}
