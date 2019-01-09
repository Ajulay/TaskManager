package com.ajulay.entity;

import java.io.Serializable;
import java.util.List;

public class Domain implements Serializable {
    private List<Project> projects;
    private List<User> assigners;
    private List<Assignee> assignees;
    private List<Task> tasks;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<User> getAssigners() {
        return assigners;
    }

    public void setAssigners(List<User> assigners) {
        this.assigners = assigners;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
