package com.ajulay.dao;

import com.ajulay.api.dao.IProjectDao;
import com.ajulay.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements IProjectDao {

    private final List<Project> projects = new ArrayList<>();

    @Override
    public Project create(String projectName) {
        Project project = new Project();
        project.setName(projectName);
        projects.add(project);
        return project;
    }

    @Override
    public Project delete(String id) throws Exception {
        List<Project> projects = findAll();
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                projects.remove(project);
                return project;
            }
        }
        throw new Exception("No such projects");
    }

    @Override
    public Project update(Project project) throws Exception {
        Project oldProject = findById(project.getId());
        oldProject.setName(project.getName());

        return oldProject;
    }

    @Override
    public Project findById(String id) throws Exception {
        for (Project project : findAll()) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        throw new Exception("No such project");
    }

    @Override
    public List<Project> findAll() {
        return projects;
    }
}
