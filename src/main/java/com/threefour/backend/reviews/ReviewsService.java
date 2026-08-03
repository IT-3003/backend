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

    public void deleteReviews(int reviewId) {
        reviewsRepository.deleteById(reviewId);
    }

    public Reviews updateReviews(int reviewId, Reviews updatedReview) {

        Reviews existingReview = reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());

        return reviewsRepository.save(existingReview);
    }

    public java.util.List<Reviews> getAllReviews() {
        return reviewsRepository.findAll();
    }
}
