package com.app.reviewms.service.impl;


import com.app.reviewms.entity.Review;
import com.app.reviewms.repository.ReviewRepository;
import com.app.reviewms.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean deleteReviewByCompanyId(Long id) {
        return false;
    }

    @Override
    public void createReview(Long companyId, Review review) {
        if(companyId != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
        }
    }

    @Override
    public Review findAllReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updateReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            updateReview.setTitle(review.getTitle());
            updateReview.setDescription(review.getDescription());
            updateReview.setRating(review.getRating());
            updateReview.setCompanyId(review.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
           reviewRepository.delete(review);
           return true;
        }
        return false;
    }
}
