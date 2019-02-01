package com.ajulay.api.service;

import com.ajulay.entity.Project;

import java.util.List;

/**
 * IProjectService creates conditions for CRUD operations for Project
 */
public interface IProjectService {


    Project findById(String projectId);

    List<Project> findAll();

    Project save(Project project);

    Project update(Project project);

    Project createProjectByName(String projectName);

    List<Project> findAllByUserId(String userId);

    List<Project> updateAll(List<Project> projects);
}
