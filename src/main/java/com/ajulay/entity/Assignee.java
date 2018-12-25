package com.ajulay.entity;

import java.util.UUID;

public class Assignee {

    private String id = UUID.randomUUID().toString();

    private String TaskId;

    private String AssignerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getAssignerId() {
        return AssignerId;
    }

    public void setAssignerId(String assignerId) {
        AssignerId = assignerId;
    }
}
