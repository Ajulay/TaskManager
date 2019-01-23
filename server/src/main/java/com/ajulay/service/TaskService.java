package com.ajulay.service;

import com.ajulay.api.dao.ITaskDAO;
import com.ajulay.api.service.ITaskService;
import com.ajulay.dao.TaskDAO;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;
import com.ajulay.exception.unchecked.NullDataForTaskException;
import com.ajulay.exception.unchecked.NullIdException;

import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class TaskService implements ITaskService {

    private final ITaskDAO dao = new TaskDAO();

    public Task saveTask(final Task task) {
        if (task == null) throw new NullDataForTaskException();
        return dao.save(task);
    }

    public Task deleteTask(final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return dao.delete(id);
    }

    public Task changeStatus(final String taskId, final String status) {
        if (taskId == null || taskId.isEmpty() || status == null || status.isEmpty()) {
            return null;
        }
        final Task task = findTaskById(taskId);
        task.setStatus(Status.valueOf(status.toUpperCase()));
        return task;
    }

    public List<Task> findTaskAll() {
        return dao.findAll();
    }

    @Override
    public List<Task> findTaskAllByProject(final String projectId) {
        if (projectId == null || projectId.isEmpty()) {
            throw new NullIdException();
        }
        return dao.findByProjectId(projectId);
    }

    @Override
    public Task findTaskById(final String taskId) {
        if (taskId == null || taskId.isEmpty()) {
            return null;
        }
        return dao.findById(taskId);
    }

    @Override
    public List<Task> merge(final List<Task> tasks) {
        if (tasks == null) return Collections.emptyList();
        return dao.merge(tasks);
    }

    public ITaskDAO getDao() {
        return dao;
    }

}
