package com.ajulay.service;

import com.ajulay.api.service.ITaskService;
import com.ajulay.dao.TaskDAO;
import com.ajulay.entity.Status;
import com.ajulay.entity.Task;

import java.util.List;

public class TaskService implements ITaskService {

    private final TaskDAO dao = new TaskDAO();

    public Task saveTask(Task task) {
        return dao.save(task);
    }

    public Task deleteTask(String id) throws Exception {
        return dao.delete(id);
    }

    public void changeStatus(String taskId, String status) throws Exception {
        for (Task task : dao.findAll()) {
            if (task.getId().equals(taskId)) {
                task.setStatus(Status.valueOf(status.toUpperCase()));
                return;
            }
        }
        throw new Exception("No such task");
    }

    public List<Task> getTasks() {
        return dao.findAll();
    }

    public void showTasks() {
        printTasks(getTasks());
    }

    @Override
    public void showTasksByProject(String projectId) {
        List<Task> tasks = dao.findByProjectId(projectId);
        printTasks(tasks);
    }

    private void printTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }
}
