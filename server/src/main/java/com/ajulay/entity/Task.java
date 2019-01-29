package com.ajulay.entity;

import com.ajulay.enumirated.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "project_id")
    private String projectId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date term;

    private int priority;

    @Column(name = "task_content")
    private String content;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.START;


    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .withZone( ZoneId.systemDefault() );
        return "Task {" +
                "id = " + getId() +
                ", projectId = '" + getProjectId() + '\'' +
                ", term = " + term.toString() +
                ", priority = " + getPriority() +
                ", content = '" + getContent() + '\'' +
                ", status = " + getStatus() +
                '}';
    }

}
