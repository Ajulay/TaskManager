package com.ajulay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "assignee")
@ApplicationScoped
@Getter
@Setter
@NoArgsConstructor
public class Assignee implements Serializable {

    @Id
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
