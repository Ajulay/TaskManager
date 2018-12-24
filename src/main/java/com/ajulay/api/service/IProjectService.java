package com.ajulay.api.service;

import com.ajulay.entity.Project;

import java.util.List;

public interface IProjectService {

    Project getByName(String projectName) throws Exception;

    void showProjects();

    List<Project> getProjects();
}
