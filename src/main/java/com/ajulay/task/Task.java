package com.ajulay.task;

import com.ajulay.task.util.Status;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Task {

    private final String id = UUID.randomUUID().toString();

    private String projectId;

    private Instant term;

    private int priority;

    private String content;

    private Enum status = Status.NEW;

    public Task() {
    }

    public Instant getTerm() {
        return term;
    }

    public void setTerm(Instant term) {
        this.term = term;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .withZone( ZoneId.systemDefault() );
        return "Task {" +
                "id = " + getId() +
                ", projectId = '" + getProjectId() + '\'' +
                ", term = " + formatter.format(getTerm()) +
                ", priority = " + getPriority() +
                ", content = '" + getContent() + '\'' +
                ", status = " + getStatus() +
                '}';
    }
}
