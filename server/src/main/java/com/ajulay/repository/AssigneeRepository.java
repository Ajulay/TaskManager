package com.ajulay.repository;

import com.ajulay.entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, String> {

    @Query("SELECT a FROM Assignee a WHERE a.userId = ?1")
    List<Assignee> findByUserId(String userId);

    @Query("SELECT a FROM Assignee a WHERE a.taskId = ?1")
    List<Assignee> findByTaskId(String taskId);

}
