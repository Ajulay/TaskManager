package com.ajulay.service;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.api.service.IProjectService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.dao.ProjectDAO;
import com.ajulay.entity.Project;
import com.ajulay.exception.NoSuchProjectException;

import java.util.List;

/**
 * @inherite
 */
public class ProjectService implements IProjectService {

    private final IProjectDAO projectDAO = new ProjectDAO();

    public Project getByName(final String projectName) throws NoSuchProjectException {
        if (projectName == null || ServiceConstant.EMPTY_VALUE.equals(projectName)) throw new NullPointerException();
        for (Project p : getProjects()) {
            if (p.getName().equals(projectName)) {
                return p;
            }
        }
        throw new NoSuchProjectException();
    }

    @Override
    public Project getById(final String projectId) throws Exception {
        if (projectId == null || ServiceConstant.EMPTY_VALUE.equals(projectId)) throw new NullPointerException();
        return projectDAO.findById(projectId);
    }

    public List<Project> getProjects() {
        return projectDAO.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        if (project == null) throw new NullPointerException();
        return projectDAO.create(project);
    }

}
