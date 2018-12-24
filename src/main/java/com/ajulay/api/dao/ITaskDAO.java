package com.ajulay.api.dao;

import com.ajulay.entity.Task;

import java.util.List;

public interface ITaskDAO {

    Task save(Task task);

    Task delete(String id) throws Exception;

    Task update(Task task) throws Exception;

    Task findById(String id) throws Exception;

    List<Task> findAll();

    List<Task> findByProjectId(String projectId);
}
