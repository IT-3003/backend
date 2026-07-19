package com.threefour.backend.reviewsandfeedback;

import org.springframework.stereotype.Service;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsandfeedbackRepository;

    public ReviewsService(ReviewsRepository reviewsandfeedbackRepository) {
        this.reviewsandfeedbackRepository = reviewsandfeedbackRepository;
    }

    public Reviews saveReviewsAndFeedback(Reviews reviewsandfeedback) {
        return reviewsandfeedbackRepository.save(reviewsandfeedback);
    }
}
