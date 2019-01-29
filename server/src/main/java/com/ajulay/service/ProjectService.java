package com.ajulay.service;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.api.service.IProjectService;
import com.ajulay.dao.ProjectDAO;
import com.ajulay.entity.Project;
import com.ajulay.exception.unchecked.NullDataForProjectException;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class ProjectService implements IProjectService {

    private final IProjectDAO projectDAO = new ProjectDAO();

    public Project getByName(final String projectName) {
        if (projectName.isEmpty()) {
            throw new NullDataForProjectException();
        }
        for (final Project p : getProjects()) {
            if (projectName.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Project getById(final String projectId) {
        if (projectId == null || projectId.isEmpty()) {
            return null;
        }
        return projectDAO.findById(projectId);
    }

    public List<Project> getProjects() {
        return projectDAO.findAll();
    }

    @Override
    public Project saveProject(final Project project) {
        if (project == null) throw new NullPointerException();
        return projectDAO.create(project);
    }

    @Override
    public List<Project> merge(final List<Project> projects) {
        if (projects == null) return null;
        return projectDAO.merge(projects);
    }

    @Override
    public Project createProjectByName(final String projectName) {
        if (projectName == null) return null;
        final Project project = new Project();
        project.setName(projectName);
        return projectDAO.create(project);
    }

    @Override
    public Project updateProject(final Project project) {
        if (project == null) return null;
        return projectDAO.update(project);
    }

    public List<Project> findProjectByUserId(final String userId) {
        if (userId == null) return Collections.emptyList();
        final List<Project> userProjects = projectDAO.findAll().stream()
                .filter((project) -> project.getAuthorId().equals(userId))
                .collect(Collectors.toList());
        return userProjects;
    }

    public IProjectDAO getProjectDAO() {
        return projectDAO;
    }

}
