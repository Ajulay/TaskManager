package com.ajulay.entity;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.UUID;


@ApplicationScoped
public class Assignee implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String taskId;

    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String assignerId) {
        this.userId = assignerId;
    }

    @Override
    public String toString() {
        return "Assignee{" +
                "id='" + id + '\'' +
                ", taskId='" + taskId + '\'' +
                ", assignerId='" + userId + '\'' +
                '}';
    }

}
