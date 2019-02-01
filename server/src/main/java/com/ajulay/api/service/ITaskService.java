package com.ajulay.api.service;



import com.ajulay.entity.Task;

import java.util.List;

/**
 * ITaskService creates conditions for CRUD operations for Task
 */
public interface ITaskService {

    Task changeStatus(String taskId, String status);

    List<Task> findAllByProject(String projectId);

    Task save(Task task);

    Task findById(String id);

    Task remove(Task task);

    Task update(Task task);

    List<Task> findAll();

    List<Task> updateAll(List<Task> tasks);

    Task removeById(String taskId);

    List<Task> findAllByUserId(String currentUser);

    List<Task> findTaskAllByUserId(String currentUserId);
}
