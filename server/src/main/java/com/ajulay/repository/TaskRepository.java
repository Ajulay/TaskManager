package com.ajulay.repository;

import com.ajulay.entity.Task;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Task.class)
public interface TaskRepository extends EntityRepository<Task, String> {

    @Query("SELECT t FROM Task t WHERE t.projectId = ?1")
    List<Task> findAllByProject(String projectId);

}
