package com.ajulay.api.service;

import com.ajulay.entity.Project;
import com.ajulay.exception.checked.NoSuchProjectException;

import java.util.List;

/**
 * IProjectService creates conditions for CRUD operations for Project
 */
public interface IProjectService {

    Project getByName(String projectName) throws NoSuchProjectException;

    Project getById(String projectId) throws NoSuchProjectException;

    List<Project> getProjects();

    Project saveProject(Project project);

    boolean merge(List<Project> projects);
}
