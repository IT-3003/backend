package com.threefour.backend.reviewsandfeedback;

import com.threefour.backend.reviewsandfeedback.ReviewsAndFeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewsAndFeedbackService {
    private final ReviewsAndFeedbackRepository reviewsandfeedbackRepository;

    public ReviewsAndFeedbackService(ReviewsAndFeedbackRepository reviewsandfeedbackRepository) {
        this.reviewsandfeedbackRepository = reviewsandfeedbackRepository;
    }

    public ReviewsAndFeedback saveReviewsAndFeedback(ReviewsAndFeedback reviewsandfeedback) {
        return reviewsandfeedbackRepository.save(reviewsandfeedback);
    }
}
