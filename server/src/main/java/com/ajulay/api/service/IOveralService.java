package com.ajulay.api.service;

import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;

import java.util.List;

public interface IOveralService {

    Project createProjectByName(final String projectName, String userId);

    List<Project> getProjectAll();

    List<Task> findTaskAllByAssignee();

    List<User> findUserAllByTaskId(String taskId);

    IAssigneeService getAssigneeService();

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    ISessionService getSessionService();

    List<Task> findTaskAllByUserId(String currentUser);
}
