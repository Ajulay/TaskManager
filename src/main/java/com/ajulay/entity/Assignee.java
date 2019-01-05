package com.ajulay.entity;

import java.io.Serializable;
import java.util.UUID;

public class Assignee implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String taskId;

    private String assignerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    @Override
    public String toString() {
        return "Assignee{" +
                "id='" + id + '\'' +
                ", taskId='" + taskId + '\'' +
                ", assignerId='" + assignerId + '\'' +
                '}';
    }

}
