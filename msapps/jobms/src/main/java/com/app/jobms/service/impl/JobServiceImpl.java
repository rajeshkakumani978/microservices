package com.app.jobms.service.impl;


import com.app.jobms.clients.CompanyClient;
import com.app.jobms.clients.ReviewClient;
import com.app.jobms.dto.JobWithCompanyDTO;
import com.app.jobms.entity.Job;
import com.app.jobms.external.Company;
import com.app.jobms.external.Review;
import com.app.jobms.repository.JobRepository;
import com.app.jobms.service.JobService;
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

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private JobWithCompanyDTO convertToDto(Job job){
        RestTemplate restTemplate = new RestTemplate();
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        Company company = restTemplate.getForObject(
                "http://localhost:8082/companies/" + job.getCompanyId(),
                    Company.class);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }
    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
       jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
       return jobRepository.findById(id).orElse(null);
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
