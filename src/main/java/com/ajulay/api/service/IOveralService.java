package com.ajulay.api.service;

import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;

import java.util.List;

public interface IOveralService {

    Project createProjectByName(final String projectName);

    List<Project> getProjectAll();

    List<Task> findTaskAllByAssignee();

    List<User> findUserAllByTaskId(String taskId);
}
