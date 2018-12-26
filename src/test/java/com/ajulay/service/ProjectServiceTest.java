package com.ajulay.service;

import com.ajulay.api.service.IProjectService;
import com.ajulay.entity.Project;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProjectServiceTest {

    @Test
    public void getByName() throws Exception {
        final IProjectService service = new ProjectService();
        final Project project = new Project();
        final String nameProject = "Project1";
        project.setName(nameProject);
        final Project projectFinded = service.getByName(nameProject);

        Assert.assertNotNull(projectFinded);
    }

    @Test
    public void getById() throws Exception {
        final IProjectService service = new ProjectService();
        final Project project = new Project();
        final Project projectFinded = service.getById(project.getId());

        Assert.assertNotNull(projectFinded);

    }

    @Test
    public void getProjects() {
        final IProjectService service = new ProjectService();
        final int controlNumber = 5;
        for (int i = 0; i < controlNumber; i++) {
            final Project project = new Project();
        }
        final List<Project> projects = service.getProjects();

        Assert.assertEquals(projects.size(), controlNumber);
    }
}