package com.threefour.backend.promotion;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/asa")
    public String hello() {
        return "Hello Anshu";
    }

    @PostMapping("/create")
    public ResponseEntity<Promotion> create(@Valid @RequestBody Promotion promotion) {
        Promotion savedPromotion = promotionService.savePromotion(promotion);
        return new ResponseEntity<>(savedPromotion, HttpStatus.CREATED);
    }

    @GetMapping("/{promotionId}")
    public Promotion getPromotionById(@PathVariable int promotionId) {
        return promotionService.getPromotionById(promotionId);
    }

    @PutMapping("/update/{promotionId}")
    public ResponseEntity<Promotion> updatePromotion(
            @PathVariable int promotionId,
            @Valid @RequestBody Promotion promotion) {

        Promotion updatedPromotion = promotionService.updatePromotion(promotionId, promotion);
        return ResponseEntity.ok(updatedPromotion);
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<String> deletePromotion(@PathVariable int promotionId) {
        promotionService.deletePromotion(promotionId);
        return ResponseEntity.ok("Promotion deleted successfully.");
    }
}