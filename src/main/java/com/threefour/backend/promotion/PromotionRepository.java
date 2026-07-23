package com.threefour.backend.promotion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    // Spring Boot automatically implements basic database methods here!
}

