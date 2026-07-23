package com.threefour.backend.reviewsandfeedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    // Spring Boot automatically implements basic database methods here!
}