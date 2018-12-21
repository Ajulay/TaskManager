package com.ajulay.service;

import com.ajulay.project.Project;

import java.util.List;

public interface ProjectService {

    Project getByName(String projectName) throws Exception;

    void showProjects();

    List<Project> getProjects();
}
