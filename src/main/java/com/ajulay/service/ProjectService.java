package com.ajulay.service;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.api.service.IProjectService;
import com.ajulay.dao.ProjectDAO;
import com.ajulay.entity.Project;
import java.util.List;

public class ProjectService implements IProjectService {

   private IProjectDAO projectDAO = new ProjectDAO();

    public Project getByName(String projectName) throws Exception {
        for (Project p : getProjects()) {
            if (p.getName().equals(projectName)) {
                return p;
            }
        }
        throw new Exception("No such project");
    }

    @Override
    public Project getById(String projectId) throws Exception {
        return projectDAO.findById(projectId);
    }

    public List<Project> getProjects() {
        return projectDAO.findAll();
    }

}
