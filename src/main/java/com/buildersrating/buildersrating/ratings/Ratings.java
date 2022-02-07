package com.buildersrating.buildersrating.ratings;

import javax.persistence.*;

@Entity
@Table(name = "tblRatings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    private int ratingNumber;
    private String reviewComment;
    private Long userId;
    private Long businessId;

    public Ratings() {
    }

    public Ratings(Long ratingId, int ratingNumber, String reviewComment, Long userId, Long businessId) {
        this.ratingId = ratingId;
        this.ratingNumber = ratingNumber;
        this.reviewComment = reviewComment;
        this.userId = userId;
        this.businessId = businessId;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "ratingId=" + ratingId +
                ", ratingNumber=" + ratingNumber +
                ", reviewComment='" + reviewComment + '\'' +
                ", userId=" + userId +
                ", businessId=" + businessId +
                '}';
    }
}
