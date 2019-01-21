package com.ajulay.dao;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.entity.Project;
import com.ajulay.exception.checked.NoSuchProjectException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
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
        for (final Project project : projects) {
            if (project.getId().equals(id)) {
                projects.remove(project);
                return project;
            }
        }
        throw new NoSuchProjectException();
    }

    @Override
    public Project update(final Project project) {
        final Project oldProject = findById(project.getId());
        oldProject.setName(project.getName());
        oldProject.setAuthorId(project.getAuthorId());
        return oldProject;
    }

    @Override
    public Project findById(final String id) {
        for (final Project project : findAll()) {
            if (id.equals(project.getId())) {
                return project;
            }
        }
        return null;
    }

    @Override
    public List<Project> findAll() {
        return projects;
    }

    @Override
    public List<Project> merge(final List<Project> projects) {
        this.projects.clear();
        this.projects.addAll(projects);
        return projects;
    }

}
