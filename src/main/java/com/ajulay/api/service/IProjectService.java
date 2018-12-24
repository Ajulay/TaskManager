package com.ajulay.api.service;

import com.ajulay.entity.Project;

import java.util.List;

public interface IProjectService {

    Project getByName(String projectName) throws Exception;

    Project getById(String projectId) throws Exception;

    List<Project> getProjects();

}
