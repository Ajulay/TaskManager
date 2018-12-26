package com.ajulay.dao;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.entity.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @inherite
 */
public class ProjectDAO implements IProjectDAO {

    private final List<Project> projects = new ArrayList<>();

    @Override
    public Project create(final Project project) {
        projects.add(project);
        return project;
    }

    @Override
    public Project delete(final String id) throws Exception {
        final List<Project> projects = findAll();
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                projects.remove(project);
                return project;
            }
        }
        throw new Exception("No such projects");
    }

    @Override
    public Project update(final Project project) throws Exception {
        final Project oldProject = findById(project.getId());
        oldProject.setName(project.getName());

        return oldProject;
    }

    @Override
    public Project findById(final String id) throws Exception {
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
