package com.threefour.backend.reviews;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Reviews {


    @Id
    private int reviewId;
    private int userId;
    private int itemId;
    private int rating;
    private String comment;

    public Reviews(int reviewId, int userId, int itemId, int rating,
                   String comment) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.itemId = itemId;
        this.rating = rating;
        this.comment = comment;
    }

    public Reviews(){
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
