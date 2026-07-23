package com.threefour.backend.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService){
        this.reviewsService = reviewsService ;
    }

    @PostMapping("/create")
    public ResponseEntity<Reviews> create(@RequestBody Reviews reviews) {
        Reviews savedReviews = reviewsService.saveReviews(reviews);
        return new ResponseEntity<>(savedReviews, HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public Reviews getReviewsById(@PathVariable int reviewId) {
        return reviewsService.getReviewsById(reviewId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviews(@PathVariable int id) {
        reviewsService.deleteReviews(id);
        return ResponseEntity.ok("Review deleted successfully.");
    }
}
