package com.app.job.service.impl;

import com.app.job.dto.Job;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job findById(Long job);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job updatedJob);
}
