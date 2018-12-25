package com.ajulay.service;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.api.service.IProjectService;
import com.ajulay.dao.ProjectDAO;
import com.ajulay.entity.Project;

import java.util.List;

/**
 * @inherite
 */
public class ProjectService implements IProjectService {

    private final IProjectDAO projectDAO = new ProjectDAO();

    public Project getByName(final String projectName) throws Exception {
        for (Project p : getProjects()) {
            if (p.getName().equals(projectName)) {
                return p;
            }
        }
        throw new Exception("No such project");
    }

    @Override
    public Project getById(final String projectId) throws Exception {
        return projectDAO.findById(projectId);
    }

    public List<Project> getProjects() {
        return projectDAO.findAll();
    }

}
