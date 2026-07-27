package com.threefour.backend.Payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Test API
    @GetMapping("/hello")
    public String hello() {
        return "Payment Service Running";
    }

    // Create Payment
    @PostMapping("/create")
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.savePayment(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    // Get All Payments
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    // Get Payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    // Update Payment
    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable int paymentId,
            @RequestBody Payment payment) {

        Payment updatedPayment = paymentService.updatePayment(paymentId, payment);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }

    // Delete Payment
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>("Payment deleted successfully.", HttpStatus.OK);
    }
}