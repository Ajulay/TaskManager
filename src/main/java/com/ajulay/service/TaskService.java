package com.ajulay.service;

import com.ajulay.dao.*;
import com.ajulay.task.AbstractTask;
import com.ajulay.task.GroupTask;
import com.ajulay.task.OveralTask;
import com.ajulay.task.PrivateTask;

import java.util.List;

public class TaskService {

    private static final GroupTaskDao groupTaskDao = new GroupTaskDaoImpl();

    private static final PrivateTaskDao privateTaskDao = new PrivateTaskDaoImpl();

    private static final OveralTaskDao overalTaskDao = new OveralTaskDaoImpl();

    public static PrivateTask createPrivateTask(String projectName, String term, int priority, String content, String executorName) throws Exception {
        return privateTaskDao.create(projectName, term, priority, content, executorName);
    }

    public static OveralTask createOveralTask(String projectName, String term, int priority, String content) {
        return overalTaskDao.create(projectName, term, priority, content);
    }

    public static GroupTask createGroupTask(String projectName, String term, int priority, String content, String... executorName) throws Exception {
        return groupTaskDao.create(projectName, term, priority, content, executorName);
    }

    public static AbstractTask deleteTask(int id) throws Exception {
        return overalTaskDao.delete(id);
    }

    public static void changeStatus(int taskId, String status) throws Exception {
        for (AbstractTask task : overalTaskDao.getTasks()) {
            if (task.getId() == taskId) {
                task.setStatus(AbstractTask.Status.valueOf(status.toUpperCase()));
                return;
            }
        }
        throw new Exception("No such task");
    }

    public static List<AbstractTask> getTasks() {
        return overalTaskDao.getTasks();
    }

    public static void showTasks() {
        for (AbstractTask task : TaskService.getTasks()) {
            System.out.println(task.toString());
        }
    }
}
