package com.threefour.backend.reviewsandfeedback;

import com.threefour.backend.reviewsandfeedback.ReviewsAndFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface ReviewsAndFeedbackRepository extends JpaRepository<ReviewsAndFeedback, Long> {
    // Spring Boot automatically implements basic database methods here!
}