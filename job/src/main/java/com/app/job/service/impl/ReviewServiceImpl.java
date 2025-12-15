package com.app.job.service.impl;

import com.app.job.entity.Company;
import com.app.job.entity.Review;
import com.app.job.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean deleteReviewByCompanyId(Long id) {
        return false;
    }

    @Override
    public void createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
        }
    }

    @Override
    public Review findAllReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(rev -> rev.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            updateReview.setCompany(company);
            updateReview.setId(reviewId);
            reviewRepository.save(updateReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company compId = companyService.getCompanyById(companyId);
        if(compId != null && reviewRepository.existsById(reviewId)){
           Review review = reviewRepository.findById(reviewId).orElse(null);
           Company company = review.getCompany();
           company.getReviews().remove(review);
           companyService.updateCompany(company, companyId);
           reviewRepository.deleteById(reviewId);
           return true;
        }
        return false;
    }
}
