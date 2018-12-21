package com.ajulay.dao;

import com.ajulay.data.Data;
import com.ajulay.task.AbstractTask;
import com.ajulay.task.GroupTask;
import com.ajulay.task.OveralTask;

import java.util.List;

public class OveralTaskDaoImpl  implements OveralTaskDao{

    private static final List<AbstractTask> tasks = Data.getTasks();

    @Override
    public OveralTask create(String projectName, String term, int priority, String content) {
        return null;
    }

    @Override
    public OveralTask delete(int id) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                return (OveralTask)task;
            }
        }
        throw new Exception("No such task");
    }

    @Override
    public OveralTask update(OveralTask task) {
        OveralTask oldTask = (OveralTask) tasks.get(task.getId());
        oldTask.setContent(task.getContent());
        oldTask.setPriority(task.getPriority());
        oldTask.setProjectName(task.getProjectName());
        oldTask.setStatus(task.getStatus());
        oldTask.setTerm(task.getTerm());
        return oldTask;
    }

    @Override
    public OveralTask findById(int id) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == id) {
                return (OveralTask) task;
            }
        }
        throw new Exception("no such task");
    }

    public List<AbstractTask> getTasks() {
        return tasks;
    }
}
