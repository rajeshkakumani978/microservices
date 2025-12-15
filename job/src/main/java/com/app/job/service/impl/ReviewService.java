package com.app.job.service.impl;

import com.app.job.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean deleteReviewByCompanyId(Long companyId);
    void createReview(Long companyId, Review review);
    Review findAllReview(Long companyId,Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review updateReview);
    boolean deleteReview(Long companyId, Long reviewId);
}
