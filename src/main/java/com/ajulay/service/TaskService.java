package com.ajulay.service;

import com.ajulay.api.service.ITaskService;
import com.ajulay.dao.TaskDAO;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;

import java.util.List;

/**
 * @inherite
 */
public class TaskService implements ITaskService {

    private final TaskDAO dao = new TaskDAO();

    public Task saveTask(final Task task) {
        return dao.save(task);
    }

    public Task deleteTask(final String id) throws Exception {
        return dao.delete(id);
    }

    public void changeStatus(final String taskId, final String status) throws Exception {
        for (Task task : dao.findAll()) {
            if (task.getId().equals(taskId)) {
                task.setStatus(Status.valueOf(status.toUpperCase()));
                return;
            }
        }
        throw new Exception("No such task");
    }

    public List<Task> findTaskAll() {
        return dao.findAll();
    }

    @Override
    public List<Task> findTaskAllByProject(final String projectId) {
        return dao.findByProjectId(projectId);
    }

    @Override
    public Task findTaskById(String taskId) throws Exception {
        return dao.findById(taskId);
    }

}
