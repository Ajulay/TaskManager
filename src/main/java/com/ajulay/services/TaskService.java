package com.ajulay.services;

import com.ajulay.executors.Executor;
import com.ajulay.tasks.AbstractTask;
import com.ajulay.tasks.GroupTask;
import com.ajulay.tasks.OveralTask;
import com.ajulay.tasks.PrivateTask;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private static final List<AbstractTask> tasks = new ArrayList<>();

    public static AbstractTask createPrivateTask(String projectName, String term, int priority, String content, String executorName){
        AbstractTask task = new PrivateTask(projectName, term, priority, content, executorName);
        tasks.add(task);
        return task;
    }

    public static AbstractTask createOveralTask(String projectName, String term, int priority, String content, String executorName){
        AbstractTask task = new OveralTask(projectName, term, priority, content);
        tasks.add(task);
        return task;
    }

    public static AbstractTask createGroupTask(String projectName, String term, int priority, String content, String executorName){
        AbstractTask task = new GroupTask(projectName, term, priority, content);
        tasks.add(task);
        return task;
    }
    
    public static AbstractTask delete(int id) throws Exception {

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id){
                tasks.remove(tasks.get(i));
                return tasks.get(i);
            }
        }
        throw new Exception("No such task");
    }

    public static void changeStatus(int taskId, String status) throws Exception {

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId){
                tasks.get(i).setStatus(AbstractTask.Status.valueOf(status.toUpperCase()));
                return;
            }
        }
        throw new Exception("No such task");
    }

    public static List<AbstractTask> getTasks() {
        return tasks;
    }

    public static void showTasks() {
        for (AbstractTask task: TaskService.getTasks()) {
            System.out.printf("ID task: %s, content: %s, status: %s\n", task.getId(), task.getContent(), task.getStatus());
        }
    }
}
