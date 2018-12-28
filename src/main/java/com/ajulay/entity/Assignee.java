package com.ajulay.entity;

import java.io.Serializable;
import java.util.UUID;

public class Assignee implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String taskId2;

    private String assignerId2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId2;
    }

    public void setTaskId(String taskId) {
        taskId = taskId;
    }

    public String getAssignerId() {
        return assignerId2;
    }

    public void setAssignerId(String assignerId) {
        assignerId = assignerId;
    }
}
