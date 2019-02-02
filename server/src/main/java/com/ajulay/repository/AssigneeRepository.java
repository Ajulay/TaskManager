package com.ajulay.repository;

import com.ajulay.api.repository.IRepository;
import com.ajulay.entity.Assignee;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Assignee.class)
public interface AssigneeRepository extends IRepository<Assignee> {

    @Query("SELECT a FROM Assignee a WHERE a.userId = ?1")
    List<Assignee> findByUserId(String userId);

    @Query("SELECT a FROM Assignee a WHERE a.taskId = ?1")
    List<Assignee> findByTaskId(String taskId);


    Assignee save(Assignee entity);

    @Query("SELECT a FROM Assignee a WHERE a.id = ?1")
    Assignee findById(String id);

    Assignee remove(Assignee entity);

    Assignee update(Assignee entity);

    List<Assignee> findAll();


}
