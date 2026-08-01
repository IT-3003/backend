package com.threefour.backend.promotion;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionById(int promotionId) {
        return promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + promotionId));
    }

    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion updatePromotion(int promotionId, Promotion updatedPromotion) {

        Promotion existingPromotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + promotionId));

        existingPromotion.setPromotionName(updatedPromotion.getPromotionName());
        existingPromotion.setDescription(updatedPromotion.getDescription());
        existingPromotion.setDiscountValue(updatedPromotion.getDiscountValue());
        existingPromotion.setDiscountType(updatedPromotion.getDiscountType());
        existingPromotion.setStartDate(updatedPromotion.getStartDate());
        existingPromotion.setEndDate(updatedPromotion.getEndDate());
        existingPromotion.setItem(updatedPromotion.getItem());

        return promotionRepository.save(existingPromotion);
    }

    public void deletePromotion(int promotionId) {

        if (!promotionRepository.existsById(promotionId)) {
            throw new RuntimeException("Promotion not found with id: " + promotionId);
        }

        promotionRepository.deleteById(promotionId);
    }
}