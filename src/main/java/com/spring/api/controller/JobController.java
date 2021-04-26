package com.spring.api.controller;

import com.spring.api.model.Job;
import com.spring.api.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@Api(value = "Jobs API Restful Web Services", description = "REST APIs related to Job Entity!!!!")
@RestController
@RequestMapping("/api")
// http://localhost:8080/api
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    // http://localhost:8080/api/jobs
    @ApiOperation(value = "Get All Jobs in the System ", response = Iterable.class, tags = "jobs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getMyJobs(@RequestParam String key ,@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<List<Job>>(jobService.getJobs(key,page,size), HttpStatus.OK);
    }

    // http://localhost:8080/api/jobs
    @ApiOperation(value = "Add New Job in the System ", response = Iterable.class, tags = "jobs")
    @PostMapping("/jobs")
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        return new ResponseEntity<Job>(jobService.addOrUpdateJob(job),HttpStatus.CREATED);
    }

    // http://localhost:8080/api/jobs?id={value}
    @ApiOperation(value = "Update Job in the System ", response = Iterable.class, tags = "jobs")
    @PutMapping("/jobs")
    public ResponseEntity<Job> editJob(@RequestParam long id,@RequestBody Job job){
        job.setId(id);
        return new ResponseEntity<Job>(jobService.addOrUpdateJob(job),HttpStatus.OK);
    }
    // http://localhost:8080/api/jobs/status?id={value}?status={value}
    @ApiOperation(value = "Update Job Status By Id in the System", response = Iterable.class, tags = "jobs/status")
    @PutMapping("jobs/status")
    public ResponseEntity<Integer> editStatus(@RequestParam int status, @RequestParam long id){
        boolean active = (status ==1)? true : false;
        return new ResponseEntity<Integer>(jobService.updateJobStatus(active,id),HttpStatus.OK);
    }



}
