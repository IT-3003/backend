package com.threefour.backend.payment;

// Custom runtime exception to handle missing payments safely
public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
