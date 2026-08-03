package com.threefour.backend.payment;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.threefour.backend.order.OrderRepository;
import com.threefour.backend.user.UserRepository;
import java.util.List;


@Service
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    // Create
    @Transactional
    public Payment savePayment(Payment payment) {
        if (payment.getOrder() != null) {
            com.threefour.backend.order.Order managedOrder = orderRepository.findById(payment.getOrder().getOrderId())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + payment.getOrder().getOrderId()));
            payment.setOrder(managedOrder);
        }
        if (payment.getUser() != null) {
            com.threefour.backend.user.User managedUser = userRepository.findById(payment.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + payment.getUser().getId()));
            payment.setUser(managedUser);
        }
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


    @Transactional
    public void deletePayment(int paymentId) {
        Payment existingPayment = getPaymentById(paymentId);
        orderRepository.clearPaymentReference(paymentId);
        paymentRepository.delete(existingPayment);
    }


    @Transactional
    public Payment updatePaymentStatusByTransaction(String transaction, PaymentStatus status) {
        Payment existingPayment = paymentRepository.findByTransaction(transaction)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with transaction: " + transaction));
        existingPayment.setPaymentStatus(status);
        return paymentRepository.save(existingPayment);
    }
}