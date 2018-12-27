package com.ajulay.service;

import com.ajulay.api.service.ITaskService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.dao.TaskDAO;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;
import com.ajulay.exception.checked.NoSuchTaskException;
import com.ajulay.exception.unchecked.NullDataForTaskException;
import com.ajulay.exception.unchecked.NullIdException;

import java.util.List;

/**
 * @inherite
 */
public class TaskService implements ITaskService {

    private final TaskDAO dao = new TaskDAO();

    public Task saveTask(final Task task) {
        if (task == null) throw new NullDataForTaskException();
        return dao.save(task);
    }

    public Task deleteTask(final String id) throws NoSuchTaskException {
        if (id == null || ServiceConstant.EMPTY_VALUE.equals(id)) {
            throw new NullIdException();
        }
        return dao.delete(id);
    }

    public void changeStatus(final String taskId, final String status) throws NoSuchTaskException {
        if (taskId == null || status == null ||
                ServiceConstant.EMPTY_VALUE.equals(taskId) ||
                ServiceConstant.EMPTY_VALUE.equals(status)) {
            throw new NullIdException();
        }
        for (Task task : dao.findAll()) {
            if (task.getId().equals(taskId)) {
                task.setStatus(Status.valueOf(status.toUpperCase()));
                return;
            }
        }
        throw new NoSuchTaskException();
    }

    public List<Task> findTaskAll() {
        return dao.findAll();
    }

    @Override
    public List<Task> findTaskAllByProject(final String projectId) {
        if (projectId == null || ServiceConstant.EMPTY_VALUE.equals(projectId)) {
            throw new NullIdException();
        }
        return dao.findByProjectId(projectId);
    }

    @Override
    public Task findTaskById(String taskId) throws NoSuchTaskException {
        if (taskId == null || ServiceConstant.EMPTY_VALUE.equals(taskId)) {
            throw new NullDataForTaskException();
        }
        return dao.findById(taskId);
    }

}
