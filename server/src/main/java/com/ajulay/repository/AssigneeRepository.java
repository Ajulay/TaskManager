package com.ajulay.repository;

import com.ajulay.entity.Assignee;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Assignee.class)
public interface AssigneeRepository extends EntityRepository<Assignee, String> {

    @Query("SELECT a FROM Assignee a WHERE a.userId = ?1")
    List<Assignee> findByUserId(String userId);

    @Query("SELECT a FROM Assignee a WHERE a.taskId = ?1")
    List<Assignee> findByTaskId(String taskId);

    @Query("SELECT a FROM Assignee a WHERE a.id = ?1")
    Assignee findById(String id);

}
