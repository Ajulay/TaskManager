package com.ajulay.dao;

import com.ajulay.task.Task;

import java.util.List;

public interface TaskDao {

    Task save(Task task);

    Task delete(String id) throws Exception;

    Task update(Task task) throws Exception;

    Task findById(String id) throws Exception;

    List<Task> findAll();

    List<Task> findByProjectId(String projectId);
}
