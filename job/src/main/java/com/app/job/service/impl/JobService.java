package com.app.job.service.impl;

import com.app.job.entity.Job;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job findById(Long job);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job updatedJob);
}
