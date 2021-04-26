package com.spring.api.dao;

import com.spring.api.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    @Query("select u FROM Job u WHERE u.code LIKE %?1% OR u.name LIKE %?1%" +
            " or if (u.status, 'active', 'notactive') LIKE %?1%")
    public Page<Job> getJobs(String key, Pageable pageable);


    @Modifying
    @Query("UPDATE Job SET status = ?1 WHERE id = ?2")
    public int editStatus(boolean status, long id);

}
