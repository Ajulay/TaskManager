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

    private List<Task> tasks;

    private List<Assignee> assignees;


    private Domain() {
    }

    public static Domain createDomain(ControllerUI controller) {
        final Domain domain = new Domain();
        final List<Project> projects = controller.getProjectService().findAll();
        final List<User> users = controller.getUserService().findAll();
        final List<Task> tasks = controller.getTaskService().findAll();
        final List<Assignee> assignees = controller.getAssigneeService().findAll();
        domain.setAssignees(assignees);
        domain.setProjects(projects);
        domain.setUsers(users);
        domain.setTasks(tasks);
        return domain;
    }

    public static Boolean loadDomain(Domain domain, ControllerUI controller) {
        final List<Project> projects = domain.getProjects();
        final List<User> users = domain.getUsers();
        final List<Task> tasks = domain.getTasks();
        final List<Assignee> assignees = domain.getAssignees();
        controller.getProjectService().updateAll(projects);
        controller.getUserService().updateAll(users);
        controller.getTaskService().updateAll(tasks);
        controller.getAssigneeService().updateAll(assignees);

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
