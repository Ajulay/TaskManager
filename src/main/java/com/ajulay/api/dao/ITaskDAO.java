package com.ajulay.api.dao;

import com.ajulay.entity.Task;
import com.ajulay.exception.checked.NoSuchTaskException;

import java.util.List;

/**
 * ITaskDAO defines base data access methods for Task
 */
public interface ITaskDAO {

    Task save(Task task);

    Task delete(String id) throws NoSuchTaskException;

    Task update(Task task) throws NoSuchTaskException;

    Task findById(String id) throws NoSuchTaskException;

    List<Task> findAll();

    List<Task> findByProjectId(String projectId);
}
