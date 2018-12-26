package com.ajulay.dao;

import com.ajulay.api.dao.ITaskDAO;
import com.ajulay.entity.Task;
import com.ajulay.exception.NoSuchTaskException;

import java.util.ArrayList;
import java.util.List;

/**
 * @inherite
 */
public class TaskDAO implements ITaskDAO {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public Task save(final Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Task delete(final String id) throws NoSuchTaskException {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                tasks.remove(task);
                return task;
            }
        }
        throw new NoSuchTaskException();
    }

    @Override
    public Task update(final Task task) throws Exception {
        final Task oldTask = findById(task.getId());
        oldTask.setContent(task.getContent());
        oldTask.setPriority(task.getPriority());
        oldTask.setProjectId(task.getProjectId());
        oldTask.setStatus(task.getStatus());
        oldTask.setTerm(task.getTerm());
        return oldTask;
    }

    @Override
    public Task findById(final String id) throws NoSuchTaskException {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        throw new NoSuchTaskException();
    }

    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public List<Task> findByProjectId(final String projectId) {
        final List<Task> projectTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getProjectId().equals(projectId)) {
                projectTasks.add(task);
            }
        }
        return projectTasks;
    }

}
