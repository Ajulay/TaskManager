package com.ajulay.service;

import com.ajulay.task.AbstractTask;
import com.ajulay.task.GroupTask;
import com.ajulay.task.OveralTask;
import com.ajulay.task.PrivateTask;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private static final List<AbstractTask> tasks = new ArrayList<>();

    public static AbstractTask createPrivateTask(String projectName, String term, int priority, String content, String executorName) throws Exception {
        final AbstractTask task = new PrivateTask(projectName, term, priority, content, executorName);
        tasks.add(task);
        return task;
    }

    public static AbstractTask createOveralTask(String projectName, String term, int priority, String content) {
        final AbstractTask task = new OveralTask(projectName, term, priority, content);
        tasks.add(task);
        return task;
    }

    public static AbstractTask createGroupTask(String projectName, String term, int priority, String content, String executorName) {
        final AbstractTask task = new GroupTask(projectName, term, priority, content, executorName);
        tasks.add(task);
        return task;
    }

    public static AbstractTask deleteTask(int id) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                return task;
            }
        }
        throw new Exception("No such task");
    }

    public static void changeStatus(int taskId, String status) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == taskId) {
                task.setStatus(AbstractTask.Status.valueOf(status.toUpperCase()));
                return;
            }
        }
        throw new Exception("No such task");
    }

    public static List<AbstractTask> getTasks() {
        return tasks;
    }

    public static void showTasks() {
        for (AbstractTask task : TaskService.getTasks()) {
            System.out.printf("ID task: %s, content: %s, status: %s, type: %s\n", task.getId(), task.getContent(), task.getStatus(), task.getClass().getSimpleName());
        }
    }
}
