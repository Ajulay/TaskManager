package com.ajulay.dao;

import com.ajulay.api.dao.ITaskDao;
import com.ajulay.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDao implements ITaskDao {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Task delete(String id) throws Exception {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                tasks.remove(task);
                return task;
            }
        }
        throw new Exception("No such task");
    }

    @Override
    public Task update(Task task) throws Exception {
        final Task oldTask = findById(task.getId());
        oldTask.setContent(task.getContent());
        oldTask.setPriority(task.getPriority());
        oldTask.setProjectId(task.getProjectId());
        oldTask.setStatus(task.getStatus());
        oldTask.setTerm(task.getTerm());
        return oldTask;
    }

    @Override
    public Task findById(String id) throws Exception {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        throw new Exception("no such task");
    }

    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public List<Task> findByProjectId(String projectId) {
        List<Task> projectTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getProjectId().equals(projectId)) {
                projectTasks.add(task);
            }
        }
        return projectTasks;
    }
}
