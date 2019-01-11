package com.ajulay.dto;

import com.ajulay.controller.ControllerUI;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;

import java.io.Serializable;
import java.util.List;

public class Domain implements Serializable {

    private List<Project> projects;

    private List<User> users;

    private List<Assignee> assignees;

    private List<Task> tasks;

    private Domain() {
    }

    public static Domain createDomain(ControllerUI controller) {
        final Domain domain = new Domain();
        final List<Project> projects = controller.getProjectService().getProjects();
        final List<User> assigners = controller.getUserService().getUsers();
        final List<Assignee> assignees = controller.getAssigneeService().findAllAssignee();
        final List<Task> tasks = controller.getTaskService().findTaskAll();
        domain.setProjects(projects);
        domain.setUsers(assigners);
        domain.setAssignees(assignees);
        domain.setTasks(tasks);
        return domain;
    }

    public static Boolean loadDomain(Domain domain, ControllerUI controller) {
        final List<Project> projects = domain.getProjects();
        final List<Assignee> assignees = domain.getAssignees();
        final List<User> assigners = domain.getUsers();
        final List<Task> tasks = domain.getTasks();
        controller.getProjectService().merge(projects);
        controller.getAssigneeService().merge(assignees);
        controller.getUserService().merge(assigners);
        controller.getTaskService().merge(tasks);
        return true;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
