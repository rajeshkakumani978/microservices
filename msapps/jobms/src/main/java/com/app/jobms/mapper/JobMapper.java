package com.app.jobms.mapper;

import com.app.jobms.dto.JobDTO;
import com.app.jobms.entity.Job;
import com.app.jobms.external.Company;
import com.app.jobms.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviewResponse){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setCompany(company);
        jobDTO.setReview(reviewResponse);
        return jobDTO;
    }

}
