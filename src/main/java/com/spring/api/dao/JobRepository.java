package com.spring.api.dao;

import com.spring.api.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    // Like  key%  %key  %key%
    public Page<Job> findByNameContaining(String name, Pageable pageable);


    @Modifying
    @Query("UPDATE Job SET status = ?1 WHERE id = ?2")
    public int editStatus(boolean status, long id);
}
