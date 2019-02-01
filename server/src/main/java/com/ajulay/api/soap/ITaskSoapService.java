package com.ajulay.api.soap;

import com.ajulay.dto.TaskView;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;

import java.util.List;

/**
 * ITaskService creates conditions for CRUD operations for Task
 */
public interface ITaskSoapService {

    Success saveTask(Session session, TaskView task);

    Success deleteTask(Session session, String id);

    Success changeStatus(Session session, String taskId, String status);

    List<TaskView> findTaskAll(Session session);

    List<TaskView> findTaskAllByProject(Session session, String project_id);

    TaskView findTaskById(Session session, String taskId);

    Success merge(Session session, List<TaskView> tasks);

    List<TaskView> findTaskAllByUserId(Session session, String currentUser);

}
