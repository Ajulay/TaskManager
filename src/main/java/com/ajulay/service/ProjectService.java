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

    public void showProjects() {
        for (Project project : getProjects()) {
            System.out.printf("Project name: %s, project id: %s.\n",project.getName(), project.getId());
        }
    }

    public List<Project> getProjects() {
        return projectDAO.findAll();
    }

}
