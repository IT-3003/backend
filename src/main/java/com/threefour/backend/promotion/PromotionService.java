package com.threefour.backend.promotion;

import org.springframework.stereotype.Service;


@Service
public class PromotionService {
    public final PromotionRepository anshuRepository;


    public PromotionService(PromotionRepository anshuRepository) {
        this.anshuRepository = anshuRepository;
    }
    public static String addnumbers(int a, int b) {
    return "The sum is "+(a+b);
    }
    public Promotion saveAnshu(Promotion anshu){
        // Directly pass the incoming object to the repository to be persisted
        return anshuRepository.save(anshu);
    }
}

