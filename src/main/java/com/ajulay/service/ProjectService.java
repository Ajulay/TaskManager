package com.ajulay.service;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.api.service.IProjectService;
import com.ajulay.dao.ProjectDAO;
import com.ajulay.entity.Project;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.unchecked.NullDataForProjectException;

import java.util.List;

/**
 * {@inheritDoc}
 */
public class ProjectService implements IProjectService {

    private final IProjectDAO projectDAO = new ProjectDAO();

    public Project getByName(final String projectName) throws NoSuchProjectException {
        if (projectName.isEmpty()) {
            throw new NullDataForProjectException();
        }
        for (final Project p : getProjects()) {
            if (projectName.equals(p.getName())) {
                return p;
            }
        }
        throw new NoSuchProjectException();
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
    public boolean merge(final List<Project> projects) {
        if (projects == null) return true;
        return projectDAO.merge(projects);
    }

}
