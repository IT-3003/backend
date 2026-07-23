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
}
