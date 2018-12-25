package com.ajulay.api.service;

import com.ajulay.entity.Task;

import java.util.List;

public interface ITaskService {

    Task saveTask(Task task);

    Task deleteTask(String id) throws Exception;

    void changeStatus(String taskId, String status) throws Exception;

    List<Task> findTaskAll();

    List<Task> findTaskAllByProject(String project_id);

    Task findTaskById(String taskId) throws Exception;
}
