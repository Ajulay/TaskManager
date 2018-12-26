package com.ajulay.dao;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.entity.Project;
import com.ajulay.exception.NoSuchProjectException;

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
    public Project delete(final String id) throws NoSuchProjectException {
        final List<Project> projects = findAll();
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                projects.remove(project);
                return project;
            }
        }
        throw new NoSuchProjectException();
    }

    @Override
    public Project update(final Project project) throws NoSuchProjectException {
        final Project oldProject = findById(project.getId());
        oldProject.setName(project.getName());

        return oldProject;
    }

    @Override
    public Project findById(final String id) throws NoSuchProjectException {
        for (Project project : findAll()) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        throw new NoSuchProjectException();
    }

    @Override
    public List<Project> findAll() {
        return projects;
    }
}
