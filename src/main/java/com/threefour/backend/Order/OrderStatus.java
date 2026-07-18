package com.threefour.backend.order;

public enum OrderStatus {
    PLACED,
    PROCESSING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
