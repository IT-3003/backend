package com.threefour.backend.payment;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;


import java.util.List;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping({"/api/payment", "/api/payments"})
@Validated
public class PaymentController {


    private final PaymentService paymentService;


    @Value("${stripe.success.url}")
    private String successUrl;


    @Value("${stripe.cancel.url}")
    private String cancelUrl;


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


    // Create Stripe Checkout Session
    @PostMapping("/stripe-session")
    public ResponseEntity<Map<String, String>> createStripeSession(@RequestBody Map<String, Object> payload) {
        try {
            Number orderIdNum = (Number) payload.get("orderId");
            int orderId = orderIdNum.intValue();
            Double amount = ((Number) payload.get("amount")).doubleValue();

            // Stripe expects amounts in cents (e.g. $10.00 = 1000 cents)
            long amountInCents = Math.round(amount * 100);


            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(successUrl + "?session_id={CHECKOUT_SESSION_ID}&orderId=" + orderId)
                    .setCancelUrl(cancelUrl)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount(amountInCents)
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Supermarket Order #" + orderId)
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();


            Session session = Session.create(params);


            Map<String, String> response = new HashMap<>();
            response.put("url", session.getUrl());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Print full exception details to console
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
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


    // Update payment status by transaction ID (called by frontend updatePaymentStatus)
    @PutMapping("/{transactionId}/status")
    public ResponseEntity<Payment> updatePaymentStatus(
            @PathVariable String transactionId,
            @RequestBody Map<String, String> payload) {
        String statusStr = payload.get("status");
        if ("COMPLETED".equalsIgnoreCase(statusStr)) {
            statusStr = "SUCCESS";
        }
        PaymentStatus status = PaymentStatus.valueOf(statusStr);
        Payment updatedPayment = paymentService.updatePaymentStatusByTransaction(transactionId, status);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }


    // Refund payment by transaction ID (called by frontend refundPayment)
    @PostMapping("/{transactionId}/refund")
    public ResponseEntity<Payment> refundPayment(@PathVariable String transactionId) {
        Payment updatedPayment = paymentService.updatePaymentStatusByTransaction(transactionId, PaymentStatus.REFUNDED);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }
}
