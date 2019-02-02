package com.ajulay.repository;

import com.ajulay.api.repository.IRepository;
import com.ajulay.entity.Task;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Task.class)
public interface TaskRepository extends IRepository<Task> {

    @Query("SELECT t FROM Task t WHERE t.id = ?1")
    Task findById(String id);

    Task save(Task entity);

    Task remove(Task entity);

    Task update(Task entity);

    List<Task> findAll();

}
