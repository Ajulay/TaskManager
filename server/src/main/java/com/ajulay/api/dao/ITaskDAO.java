package com.ajulay.api.dao;

import com.ajulay.entity.Task;

import java.sql.Connection;
import java.util.List;

/**
 * ITaskDAO defines base data access methods for Task
 */
public interface ITaskDAO {

    Task save(Task task);

    Task delete(String id);

    Task update(Task task);

    Task findById(String id);

    List<Task> findAll();

    List<Task> findByProjectId(String projectId);

    List<Task> merge(List<Task> tasks);

    void setConn(Connection conn);

}
