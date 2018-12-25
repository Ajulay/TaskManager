package com.ajulay.api.service;

import com.ajulay.entity.Project;

import java.util.List;

/**
 * IProjectService creates conditions for CRUD operations for Project
 */
public interface IProjectService {

    Project getByName(String projectName) throws Exception;

    Project getById(String projectId) throws Exception;

    List<Project> getProjects();

}
