package com.ajulay.api.service;


import com.ajulay.entity.Task;

import java.util.List;

/**
 * ITaskService creates conditions for CRUD operations for Task
 */
public interface ITaskService {

    Task saveTask(Task task);

    Task deleteTask(String id);

    Task changeStatus(String taskId, String status);

    List<Task> findTaskAll();

    List<Task> findTaskAllByProject(String project_id);

    Task findTaskById(String taskId);

    List<Task> merge(List<Task> tasks);

}
