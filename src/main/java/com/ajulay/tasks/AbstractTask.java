package com.ajulay.tasks;

import com.ajulay.projects.Project;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public abstract class AbstractTask {

    private static int counter;
    private int id;

    private String projectName;
    private Instant term;
    private int priority;
    private String content;
    private Enum status;

    public AbstractTask(String project, String term, int priority, String content) {
        id = counter++;
        this.projectName = project;
        this.priority = priority;
        this.content = content;
        status = Status.NEW;

        String[] datePartArray = term.split("-");
        this.term = LocalDate.of(
                Integer.parseInt(datePartArray[0]), Integer.parseInt(datePartArray[1]), Integer.parseInt(datePartArray[2]))
                .atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    public String getProject() {
        return projectName;
    }

    public void setProject(String project) {
        this.projectName = project;
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

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public enum Status{
        NEW, ONWORKING, FINISHED, FAILED
    }
}
