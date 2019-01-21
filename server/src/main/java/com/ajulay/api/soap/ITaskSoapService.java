package com.ajulay.api.soap;

import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;
import com.ajulay.entity.Task;

import java.util.List;

/**
 * ITaskService creates conditions for CRUD operations for Task
 */
public interface ITaskSoapService {

    Success saveTask(Session session, Task task);

    Success deleteTask(Session session, String id);

    Success changeStatus(Session session, String taskId, String status);

    List<Task> findTaskAll(Session session);

    List<Task> findTaskAllByProject(Session session, String project_id);

    Task findTaskById(Session session, String taskId);

    Success merge(Session session, List<Task> tasks);

    List<Task> findTaskAllByUserId(Session session, String currentUser);

}
