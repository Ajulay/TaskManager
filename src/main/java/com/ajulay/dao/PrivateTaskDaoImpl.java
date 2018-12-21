package com.ajulay.dao;

import com.ajulay.data.Data;
import com.ajulay.task.AbstractTask;
import com.ajulay.task.GroupTask;
import com.ajulay.task.PrivateTask;

import java.util.List;

public class PrivateTaskDaoImpl implements PrivateTaskDao{

    private static final List<AbstractTask> tasks = Data.getTasks();

    @Override
    public PrivateTask create(String projectName, String term, int priority, String content, String executorName) throws Exception {
        return null;
    }

    @Override
    public PrivateTask delete(int id) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                return (PrivateTask)task;
            }
        }
        throw new Exception("No such task");
    }

    @Override
    public PrivateTask update(PrivateTask task) {
        PrivateTask oldTask = (PrivateTask) tasks.get(task.getId());
        oldTask.setExecutor(task.getExecutor());
        oldTask.setContent(task.getContent());
        oldTask.setPriority(task.getPriority());
        oldTask.setProjectName(task.getProjectName());
        oldTask.setStatus(task.getStatus());
        oldTask.setTerm(oldTask.getTerm());
        return oldTask;
    }

    @Override
    public PrivateTask findById(int id) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == id) {
                return (PrivateTask) task;
            }
        }
        throw new Exception("no such task");
    }
}
