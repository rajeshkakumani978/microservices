package com.app.reviewms.service;


import com.app.reviewms.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Long companyId);
    boolean deleteReviewByCompanyId(Long companyId);
    void createReview(Long companyId, Review review);
    Review findAllReview(Long reviewId);
    boolean updateReview(Long reviewId, Review updateReview);
    boolean deleteReview(Long reviewId);
}
