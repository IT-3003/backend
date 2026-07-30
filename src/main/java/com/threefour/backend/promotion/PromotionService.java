package com.threefour.backend.promotion;

import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion getPromotionById(int promotionId) {
        return promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + promotionId));
    }

    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public void deletePromotion(int promotionId) {
        if (!promotionRepository.existsById(promotionId)) {
            throw new RuntimeException("Promotion not found with id: " + promotionId);
        }
        promotionRepository.deleteById(promotionId);
    }
}

