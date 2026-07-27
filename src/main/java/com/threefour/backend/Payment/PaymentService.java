package com.threefour.backend.Payment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Create
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Read by ID
    public Payment getPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found with id: " + paymentId));
    }

    // Read All
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Update
    public Payment updatePayment(int paymentId, Payment payment) {

        if (!paymentRepository.existsById(paymentId)) {
            throw new RuntimeException("Payment not found.");
        }

        payment.setPaymentId(paymentId);

        return paymentRepository.save(payment);
    }

    // Delete
    public void deletePayment(int paymentId) {

        if (!paymentRepository.existsById(paymentId)) {
            throw new RuntimeException("Payment not found.");
        }

        paymentRepository.deleteById(paymentId);
    }
}