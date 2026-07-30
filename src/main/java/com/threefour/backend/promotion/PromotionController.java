package com.threefour.backend.promotion;

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
    public ResponseEntity<Promotion> create(@RequestBody Promotion promotion) {
        Promotion savedPromotion = promotionService.savePromotion(promotion);
        return new ResponseEntity<>(savedPromotion, HttpStatus.CREATED);
    }

    @GetMapping("/{promotionId}")
    public Promotion getUserById(@PathVariable int promotionId) {
        return promotionService.getPromotionById(promotionId);
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<String> deletePromotion(@PathVariable int promotionId) {
        promotionService.deletePromotion(promotionId);
        return ResponseEntity.ok("Promotion deleted successfully.");

    }
}