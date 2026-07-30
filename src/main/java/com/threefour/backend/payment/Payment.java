package com.threefour.backend.payment;

import com.threefour.backend.order.Order;
import com.threefour.backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @NotNull(message = "Order is required")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    @Column(name = "amount")
    private Double amount;

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
                   Order order,
                   User user,
                   Double amount,
                   PaymentMethod paymentMethod,
                   String transaction,
                   PaymentStatus paymentStatus,
                   LocalDateTime paymentDate,
                   boolean isActive) {

        this.paymentId = paymentId;
        this.order = order;
        this.user = user;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transaction = transaction;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.isActive = isActive;
    }

    // Getters
    public int getPaymentId() { return paymentId; }
    public Order getOrder() { return order; }
    public User getUser() { return user; }
    public Double getAmount() { return amount; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public String getTransaction() { return transaction; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public boolean isActive() { return isActive; }

    // Setters
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public void setOrder(Order order) { this.order = order; }
    public void setUser(User user) { this.user = user; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setTransaction(String transaction) { this.transaction = transaction; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public void setActive(boolean active) { isActive = active; }
}