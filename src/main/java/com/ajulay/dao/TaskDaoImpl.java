package com.ajulay.dao;

import com.ajulay.data.Data;
import com.ajulay.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    @Override
    public Task save(Task task) {
        Data.getTasks().add(task);
        return task;
    }

    @Override
    public Task delete(String id) throws Exception {
        List<Task> tasks = Data.getTasks();
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
        Task oldTask = findById(task.getId());
        oldTask.setContent(task.getContent());
        oldTask.setPriority(task.getPriority());
        oldTask.setProjectId(task.getProjectId());
        oldTask.setStatus(task.getStatus());
        oldTask.setTerm(task.getTerm());
        return oldTask;
    }

    @Override
    public Task findById(String id) throws Exception {
        for (Task task : Data.getTasks()) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        throw new Exception("no such task");
    }

    public List<Task> findAll() {
        return Data.getTasks();
    }

    @Override
    public List<Task> findByProjectId(String projectId) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : findAll()) {
            if (task.getProjectId().equals(projectId)) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
