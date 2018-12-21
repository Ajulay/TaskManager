package com.ajulay.service;

import com.ajulay.task.Task;

import java.util.List;

public interface TaskService {

    Task saveTask(Task task);

    Task deleteTask(String id) throws Exception;

    void changeStatus(String taskId, String status) throws Exception;

    List<Task> getTasks();

    void showTasks();
}
