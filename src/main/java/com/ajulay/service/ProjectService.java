package com.ajulay.service;

import com.ajulay.api.service.IProjectService;
import com.ajulay.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectService implements IProjectService {

    private final List<Project> projects = new ArrayList<>();

    public Project getByName(String projectName) throws Exception {
        for (Project p : projects) {
            if (p.getName().equals(projectName)) {
                return p;
            }
        }
        throw new Exception("No such project");
    }

    public void showProjects() {
        for (Project project : projects) {
            System.out.printf("Project name: %s, project id: %s.\n",project.getName(), project.getId());
        }
    }

    public List<Project> getProjects() {
        return projects;
    }

}
