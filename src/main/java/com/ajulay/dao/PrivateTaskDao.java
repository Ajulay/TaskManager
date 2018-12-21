package com.ajulay.dao;

import com.ajulay.task.GroupTask;
import com.ajulay.task.PrivateTask;

public interface PrivateTaskDao {

    PrivateTask create(String projectName, String term, int priority, String content, String executorName) throws Exception;

    PrivateTask delete(int id) throws Exception;

    PrivateTask update(PrivateTask task);

    PrivateTask findById(int id) throws Exception;
}
