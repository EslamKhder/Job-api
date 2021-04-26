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

    // http://localhost:8080/api/jobs?page={value}&size={value}
    @ApiOperation(value = "Get All Jobs in the System ", response = Iterable.class, tags = "jobs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> allJobs(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<List<Job>>(jobService.getAllJobs(page,size), HttpStatus.OK);
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

    // http://localhost:8080/api/jobs/search/key?name={value}&page={value}&size={value}
    @ApiOperation(value = "Get Jobs By Name in the System ", response = Iterable.class, tags = "jobs/search/key")
    @GetMapping("jobs/search/key")
    public ResponseEntity<List<Job>> getJobByKey(@RequestParam String name,@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<List<Job>>(jobService.getJobsByKey(name,page,size),HttpStatus.OK);
    }

    // http://localhost:8080/api/jobs/search/code?code={value}&page={value}&size={value}
    @ApiOperation(value = "Get Job By Code in the System ", response = Iterable.class, tags = "jobs/search/code")
    @GetMapping("jobs/search/code")
    public ResponseEntity<Job> getJobByCode(@RequestParam String code){
        return new ResponseEntity<Job>(jobService.getJobsByCode(code),HttpStatus.OK);
    }

    // http://localhost:8080/api/jobs/search/status?status={value}&page={value}&size={value}
    @ApiOperation(value = "Get Job By Status in the System ", response = Iterable.class, tags = "jobs/search/status")
    @GetMapping("jobs/search/status")
    public ResponseEntity<List<Job>> getJobByStatus(@RequestParam String status,@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<List<Job>>(jobService.getJobsByStatus(status,page,size),HttpStatus.OK);
    }

    // http://localhost:8080/api/jobs/status?id={value}?status={value}
    @ApiOperation(value = "Update Job Status By Id in the System", response = Iterable.class, tags = "jobs/status")
    @PutMapping("jobs/status")
    public ResponseEntity<Integer> editStatus(@RequestParam int status, @RequestParam long id){
        boolean active = (status ==1)? true : false;
        return new ResponseEntity<Integer>(jobService.updateJobStatus(active,id),HttpStatus.OK);
    }
}
