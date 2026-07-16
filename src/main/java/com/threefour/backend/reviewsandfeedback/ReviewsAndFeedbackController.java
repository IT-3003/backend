package com.threefour.backend.reviewsandfeedback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviewsandfeedback")
public class ReviewsAndFeedbackController {

    private final ReviewsAndFeedbackService reviewsandfeedbackService;

    public ReviewsAndFeedbackController(ReviewsAndFeedbackService reviewsandfeedbackService){
        this.reviewsandfeedbackService = reviewsandfeedbackService ;
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewsAndFeedback> create(@RequestBody ReviewsAndFeedback reviewsandfeedback) {
        ReviewsAndFeedback savedReviewsAndFeedback = reviewsandfeedbackService.saveReviewsAndFeedback(reviewsandfeedback);
        return new ResponseEntity<>(savedReviewsAndFeedback, HttpStatus.CREATED);
    }
}
