package com.threefour.backend.reviewsandfeedback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviewsandfeedback")
public class ReviewsController {

    private final ReviewsService reviewsandfeedbackService;

    public ReviewsController(ReviewsService reviewsandfeedbackService){
        this.reviewsandfeedbackService = reviewsandfeedbackService ;
    }

    @PostMapping("/create")
    public ResponseEntity<Reviews> create(@RequestBody Reviews reviewsandfeedback) {
        Reviews savedReviews = reviewsandfeedbackService.saveReviewsAndFeedback(reviewsandfeedback);
        return new ResponseEntity<>(savedReviews, HttpStatus.CREATED);
    }
}
