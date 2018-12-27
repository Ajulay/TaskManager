package com.ajulay.api.service;

import com.ajulay.entity.Task;
import com.ajulay.exception.checked.NoSuchTaskException;

import java.util.List;

/**
 * ITaskService creates conditions for CRUD operations for Task
 */
public interface ITaskService {

    Task saveTask(Task task);

    Task deleteTask(String id) throws NoSuchTaskException;

    void changeStatus(String taskId, String status) throws NoSuchTaskException;

    List<Task> findTaskAll();

    List<Task> findTaskAllByProject(String project_id);

    Task findTaskById(String taskId) throws NoSuchTaskException;
}
