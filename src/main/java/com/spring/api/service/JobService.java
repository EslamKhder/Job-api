package com.spring.api.service;

import com.spring.api.dao.JobRepository;
import com.spring.api.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {


    private JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Transactional
    public List<Job> getJobs(String key,int page,int size){
        boolean active = false;
        Pageable pageable = PageRequest.of(page,size,Sort.by("name"));
        return jobRepository.getJobs(key,pageable).getContent();
    }

    public Job addOrUpdateJob(Job job){
        return jobRepository.save(job);
    }

    @Transactional
    public int updateJobStatus(boolean status, long id){
        return jobRepository.editStatus(status, id);
    }
}
