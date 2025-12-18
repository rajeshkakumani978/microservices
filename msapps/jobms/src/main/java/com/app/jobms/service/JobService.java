package com.app.jobms.service;

import com.app.jobms.dto.JobWithCompanyDTO;
import com.app.jobms.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job findById(Long job);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job updatedJob);
}
