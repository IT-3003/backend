package com.threefour.backend.Order;

import com.threefour.backend.order.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;                  // Unique order identifier
    private int userId;                   // FK -> Customer who placed the order
    private int branchId;                 // FK -> Branch fulfilling the order

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<com.threefour.backend.order.OrderItem> orderItems;   // Items included in this order
    private double subtotal;              // Sum before discount
    private double discountAmount;        // Applied from Promotion (if any)
    private String couponCode;            // Coupon used, nullable
    private double totalAmount;           // Final payable amount
    private com.threefour.backend.order.OrderStatus status;           // Enum: current fulfillment state
    private String deliveryAddress;       // Snapshot of address at order time
    private int paymentId;                // FK -> Payment record
    private Date orderDate;               // When order was placed
    private Date updatedDate;             // Last status change timestamp

    public Order(Date updatedDate, Date orderDate, int paymentId, String deliveryAddress, OrderStatus status, double totalAmount, String couponCode, double discountAmount, double subtotal, List<com.threefour.backend.order.OrderItem> orderItems, int branchId, int userId, int orderId) {
        this.updatedDate = updatedDate;
        this.orderDate = orderDate;
        this.paymentId = paymentId;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.totalAmount = totalAmount;
        this.couponCode = couponCode;
        this.discountAmount = discountAmount;
        this.subtotal = subtotal;
        this.orderItems = orderItems;
        this.branchId = branchId;
        this.userId = userId;
        this.orderId = orderId;
    }

    public Order(){

    }

    public int getOrderId(){
        return orderId;
    }
    public void setOrderId(int orderId){
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

    public List<com.threefour.backend.order.OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<com.threefour.backend.order.OrderItem> orderItems) {
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

    public com.threefour.backend.order.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(com.threefour.backend.order.OrderStatus status) {
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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public double calculateTotal() {
        return subtotal - discountAmount;
    }
}
