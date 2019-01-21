package com.ajulay.api.service;

import com.ajulay.entity.Project;

import java.util.List;

/**
 * IProjectService creates conditions for CRUD operations for Project
 */
public interface IProjectService {

    Project getByName(String projectName);

    Project getById(String projectId);

    List<Project> getProjects();

    Project saveProject(Project project);

    List<Project> merge(List<Project> projects);

    Project createProjectByName(String projectName);

    Project updateProject(Project project);

    List<Project> findProjectByUserId(String userId);
}
