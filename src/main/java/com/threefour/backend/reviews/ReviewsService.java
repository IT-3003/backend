package com.threefour.backend.reviews;

import org.springframework.stereotype.Service;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public Reviews saveReviews(Reviews reviews) {
        return reviewsRepository.save(reviews);
    }

    public Reviews getReviewsById(int reviewsId) {
        // Fetches all columns for the specific primary key
        return reviewsRepository.findById(reviewsId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + reviewsId));
    }
}
