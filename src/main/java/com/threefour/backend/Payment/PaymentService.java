package com.threefour.backend.Payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository ;

public PaymentService(PaymentRepository paymentRepository){
    this.paymentRepository = paymentRepository ;
}

    public Payment savePayment(Payment payment){
        // Directly pass the incoming object to the repository to be persisted
        return paymentRepository.save(payment);
    }


    public static String addnumbers(int num1, int num2) {
    return "The sum is " + (num1+num2) ;
    }



}
