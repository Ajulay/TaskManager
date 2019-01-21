package com.ajulay.service;


import com.ajulay.api.service.*;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.unchecked.NoCurrentUserException;
import com.ajulay.exception.unchecked.NoRightEnoughException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OveralService implements IOveralService {

    private final IAssigneeService assigneeService;

    private final IUserService userService;

    private final IProjectService projectService;

    private final ITaskService taskService;

    private ISessionService sessionService;

    public OveralService(IAssigneeService assigneeService, IUserService userService, IProjectService projectService,
                         ITaskService taskService, ISessionService sessionService) {
        this.assigneeService = assigneeService;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @Override
    public Project createProjectByName(final String projectName, final String userId) {
        if (projectName == null || projectName.isEmpty()) return null;
        final User currentUser = userService.findById(userId);
        if (currentUser == null) throw new NoCurrentUserException();
        //if (Role.WORKER.equals(currentUser.getRole())) throw new NoRightEnoughException();
        final Project project = new Project();
        project.setName(projectName);
        project.setAuthorId(currentUser.getId());
        return projectService.saveProject(project);
    }

    @Override
    public List<Project> getProjectAll() {
        final User currentUser = userService.getCurrentUser();
        if (currentUser == null) throw new NoCurrentUserException();
        if (Role.ADMIN.equals(currentUser.getRole())) {
            return projectService.getProjects();
        }
        final List<Project> projects = new ArrayList<>();
        if (Role.MANAGER.equals(currentUser.getRole())) {
            for (final Project project : projectService.getProjects()) {
                if (project.getAuthorId().equals(currentUser.getId())) {
                    projects.add(project);
                }
            }
        }
        if (Role.WORKER.equals(currentUser)) throw new NoRightEnoughException();

        return projects;
    }

    public List<Task> findTaskAllByAssignee() {
        final List<Assignee> assignees = assigneeService.findAllAssignee();
        final User currentUser = userService.getCurrentUser();
        if (currentUser == null) return null;
        final List<Task> tasks = new ArrayList<>();
        for (final Assignee assignee : assignees) {
            if (currentUser.getId().equals(assignee.getAssignerId())) {
                final Task task = taskService.findTaskById(assignee.getTaskId());
                tasks.add(task);
            }
        }
        return tasks;
    }

    public List<User> findUserAllByTaskId(final String taskId) {
        final List<Assignee> assignees = assigneeService.findAllAssignee();
        final Task task = taskService.findTaskById(taskId);
        if (task == null) return Collections.emptyList();
        final List<User> users = new ArrayList<>();
        for (final Assignee assignee : assignees) {
            if (task.getId().equals(assignee.getTaskId())) {
                final User user = userService.findById(assignee.getAssignerId());
                users.add(user);
            }
        }
        return users;
    }


    public IAssigneeService getAssigneeService() {
        return assigneeService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public ISessionService getSessionService() {
        return sessionService;
    }

    @Override
    public List<Task> findTaskAllByUserId(final String currentUser) {
        final List<Assignee> assignees = assigneeService.findAssigneeAllByUserId(currentUser);
        final List<Task> tasks = new ArrayList<>();
        for (final Assignee assignee : assignees) {
            final Task task = taskService.findTaskById(assignee.getTaskId());
            if (task != null)
                tasks.add(task);
        }
        return tasks;
    }

}
