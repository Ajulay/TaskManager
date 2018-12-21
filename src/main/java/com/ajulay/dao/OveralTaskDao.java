package com.ajulay.dao;

import com.ajulay.task.AbstractTask;
import com.ajulay.task.OveralTask;

import java.util.List;

public interface OveralTaskDao {

    OveralTask create(String projectName, String term, int priority, String content);

    OveralTask delete(int id) throws Exception;

    OveralTask update(OveralTask task);

    OveralTask findById(int id) throws Exception;

    List<AbstractTask> getTasks();
}
