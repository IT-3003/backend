package com.threefour.backend.promotion;

import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    // Get Promotion by ID
    public Promotion getPromotionById(int promotionId) {
        return promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + promotionId));
    }

    // Create Promotion
    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    // Update Promotion
    public Promotion updatePromotion(int promotionId, Promotion updatedPromotion) {

        Promotion existingPromotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + promotionId));

        existingPromotion.setPromotionName(updatedPromotion.getPromotionName());
        existingPromotion.setDescription(updatedPromotion.getDescription());
        existingPromotion.setDiscountValue(updatedPromotion.getDiscountValue());
        existingPromotion.setDiscountType(updatedPromotion.getDiscountType());
        existingPromotion.setStartDate(updatedPromotion.getStartDate());
        existingPromotion.setEndDate(updatedPromotion.getEndDate());
        existingPromotion.setItemId(updatedPromotion.getItemId());

        return promotionRepository.save(existingPromotion);
    }

    // Delete Promotion
    public void deletePromotion(int promotionId) {
        if (!promotionRepository.existsById(promotionId)) {
            throw new RuntimeException("Promotion not found with id: " + promotionId);
        }

        promotionRepository.deleteById(promotionId);
    }
}