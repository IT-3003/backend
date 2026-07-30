package com.threefour.backend.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Create
    @Transactional
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Read by ID
    public Payment getPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new PaymentNotFoundException("Payment not found with id: " + paymentId));
    }

    // Read All
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Update
    @Transactional
    public Payment updatePayment(int paymentId, Payment paymentDetails) {
        Payment existingPayment = getPaymentById(paymentId);

        existingPayment.setOrder(paymentDetails.getOrder());
        existingPayment.setUser(paymentDetails.getUser());
        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
        existingPayment.setTransaction(paymentDetails.getTransaction());
        existingPayment.setPaymentStatus(paymentDetails.getPaymentStatus());
        existingPayment.setPaymentDate(paymentDetails.getPaymentDate());
        existingPayment.setActive(paymentDetails.isActive());

        return paymentRepository.save(existingPayment);
    }

    // Soft Delete
    @Transactional
    public void deletePayment(int paymentId) {
        Payment existingPayment = getPaymentById(paymentId);

        existingPayment.setActive(false);
        paymentRepository.save(existingPayment);
    }
}