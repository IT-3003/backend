package com.threefour.backend.order;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.threefour.backend.user.User;
import com.threefour.backend.branch.Branch;
import com.threefour.backend.payment.Payment;


@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {


    @Id
    @Column(name = "order_id")
    private int orderId;


    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;


    @Column(name = "branch_id", insertable = false, updatable = false)
    private int branchId;


    @NotEmpty(message = "Order must contain at least one item")
    @Valid // Critical for nested validation of items inside the list
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;


    @PositiveOrZero(message = "Subtotal cannot be negative")
    private double subtotal;


    @PositiveOrZero(message = "Discount amount cannot be negative")
    private double discountAmount;


    private String couponCode;


    @Positive(message = "Total amount must be greater than zero")
    private double totalAmount;


    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @NotBlank(message = "Delivery address cannot be blank")
    private String deliveryAddress;


    @Column(name = "payment_id", insertable = false, updatable = false)
    private Integer paymentId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;


    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;


    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;


    public Order() {
    }


    public Order(int orderId, int userId, int branchId, List<OrderItem> orderItems,
                 double subtotal, double discountAmount, String couponCode,
                 double totalAmount, OrderStatus status,
                 String deliveryAddress, Integer paymentId,
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


    public Integer getPaymentId() {
        return paymentId;
    }


    public void setPaymentId(Integer paymentId) {
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Branch getBranch() {
        return branch;
    }


    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    public Payment getPayment() {
        return payment;
    }


    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}


