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

    public List<Job> getAllJobs(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("name"));
        return jobRepository.findAll(pageable).getContent();
    }
    public Job addOrUpdateJob(Job job){
        return jobRepository.save(job);
    }

    public List<Job> getJobsByKey(String key,int page,int size){
        Pageable pageable = PageRequest.of(page,size,Sort.by("name"));
        return jobRepository.findByNameContaining(key,pageable).getContent();
    }

    public Job getJobsByCode(String code){
        return jobRepository.findByCode(code);
    }
    public List<Job> getJobsByStatus(String status,int page,int size){
        boolean active = false;
        if(status.equalsIgnoreCase("active")){
            active = true;
        } else if(status.equalsIgnoreCase("notactive")){
            active = false;
        } else {
            return new ArrayList<Job>();
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by("name"));
        return jobRepository.findByStatus(active,pageable).getContent();
    }
    @Transactional
    public int updateJobStatus(boolean status, long id){
        return jobRepository.editStatus(status, id);
    }
}
