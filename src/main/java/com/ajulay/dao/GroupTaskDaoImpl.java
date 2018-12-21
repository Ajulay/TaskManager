package com.ajulay.dao;

import com.ajulay.data.Data;
import com.ajulay.task.AbstractTask;
import com.ajulay.task.GroupTask;
import com.ajulay.task.OveralTask;

import java.util.ArrayList;
import java.util.List;

public class GroupTaskDaoImpl implements GroupTaskDao {

    private final List<AbstractTask> tasks = Data.getTasks();

    @Override
    public GroupTask create(String projectName, String term, int priority, String content, String... executorName) throws Exception {
        final AbstractTask task = new OveralTask(projectName, term, priority, content);
        tasks.add(task);
        return null;
    }

    @Override
    public GroupTask delete(int id) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                return (GroupTask) task;
            }
        }
        throw new Exception("No such task");
    }

    @Override
    public GroupTask update(GroupTask task) {
        GroupTask oldTask = (GroupTask) tasks.get(task.getId());
        oldTask.setExecutors(task.getExecutors());
        oldTask.setContent(task.getContent());
        oldTask.setPriority(task.getPriority());
        oldTask.setProjectName(task.getProjectName());
        oldTask.setStatus(task.getStatus());
        oldTask.setTerm(oldTask.getTerm());
        return oldTask;
    }

    @Override
    public GroupTask findById(int id) throws Exception {
        for (AbstractTask task : tasks) {
            if (task.getId() == id) {
                return (GroupTask) task;
            }
        }
        throw new Exception("no such task");
    }
}