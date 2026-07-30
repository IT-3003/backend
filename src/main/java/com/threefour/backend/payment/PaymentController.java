package com.threefour.backend.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Test API
    @GetMapping("/hello")
    public String hello() {
        return "payment Service Running";
    }

    // Create payment
    @PostMapping("/create")
    public ResponseEntity<Payment> create(@Valid @RequestBody Payment payment) {
        Payment savedPayment = paymentService.savePayment(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    // Get All Payments
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    // Get payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(
            @PathVariable @Min(value = 1, message = "Payment ID must be greater than 0") int paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    // Update payment
    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable @Min(value = 1, message = "Payment ID must be greater than 0") int paymentId,
            @Valid @RequestBody Payment payment) {

        Payment updatedPayment = paymentService.updatePayment(paymentId, payment);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }

    // Delete payment
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(
            @PathVariable @Min(value = 1, message = "Payment ID must be greater than 0") int paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>("payment deleted successfully.", HttpStatus.OK);
    }
}