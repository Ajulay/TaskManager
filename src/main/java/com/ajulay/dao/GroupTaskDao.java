package com.ajulay.dao;


import com.ajulay.task.AbstractTask;
import com.ajulay.task.GroupTask;


public interface GroupTaskDao {

    GroupTask create(String projectName, String term, int priority, String content, String... executorName) throws Exception;

    GroupTask delete(int id) throws Exception;

    GroupTask update(GroupTask task);

    GroupTask findById(int id) throws Exception;
}
