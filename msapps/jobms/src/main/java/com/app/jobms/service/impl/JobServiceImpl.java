package com.app.jobms.service.impl;


import com.app.jobms.clients.CompanyClient;
import com.app.jobms.clients.ReviewClient;
import com.app.jobms.dto.JobDTO;
import com.app.jobms.entity.Job;
import com.app.jobms.external.Company;
import com.app.jobms.external.Review;
import com.app.jobms.mapper.JobMapper;
import com.app.jobms.repository.JobRepository;
import com.app.jobms.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    //private Long nextId = 1L;

    private JobRepository jobRepository;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;

//    @Autowired
//    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    private JobDTO convertToDto(Job job){
//        Company company = restTemplate.getForObject(
//                "http://COMPANY-SERVICE:8082/companies/" + job.getCompanyId(),
//                    Company.class);
//        ResponseEntity<List<Review>> reviewResponse= restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });
 //       List<Review> reviews = reviewResponse.getBody();

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
        jobDTO.setCompany(company);
        jobDTO.setReview(reviews);
        return jobDTO;
    }
    @Override
   // @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyFallBackMethod")
   // @Retry(name = "companyBreaker", fallbackMethod = "companyFallBackMethod")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyFallBackMethod")
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<String> companyFallBackMethod(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
       jobRepository.save(job);
    }

    @Override
    public JobDTO findById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setId(updatedJob.getId());
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }


}
