package com.spring.api.controller;

import com.spring.api.model.Job;
import com.spring.api.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@RestController
@RequestMapping("/api")
// http://localhost:8080/api
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // http://localhost:8080/api/jobs?page={value}&size={value}
    @GetMapping("/jobs")
    public List<Job> allJobs(@RequestParam int page, @RequestParam int size){
        return jobService.getAllJobs(page,size);
    }

    // http://localhost:8080/api/jobs
    @PostMapping("/jobs")
    public Job addJob(@RequestBody Job job){
        return jobService.addOrUpdateJob(job);
    }

    // http://localhost:8080/api/jobs?id={value}
    @PutMapping("/jobs")
    public Job editJob(@RequestParam long id,@RequestBody Job job){
        job.setId(id);
        return jobService.addOrUpdateJob(job);
    }

    // http://localhost:8080/api/jobs/search?name={value}&page={value}&size={value}
    @GetMapping("jobs/search")
    public List<Job> getJobByKey(@RequestParam String name,@RequestParam int page,@RequestParam int size){
        return jobService.getJobsByKey(name,page,size);
    }

    // http://localhost:8080/api/jobs/status?id={value}?status=
    @PutMapping("jobs/status")
    public int editStatus(@RequestParam int status,@RequestParam long id){
        boolean active = (status ==1)? true : false;
        return jobService.updateJobStatus(active,id);
    }
}
