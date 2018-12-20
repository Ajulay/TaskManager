package com.ajulay.service;

import com.ajulay.project.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private static List<Project> projects = new ArrayList<>();

    public Project getByName(String projectName) throws Exception {
        for (Project p : projects) {
            if (p.getName().equals(projectName)) {
                return p;
            }
        }
        throw new Exception("No such project");
    }

    public static void showProjects() {
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    public static List<Project> getProjects() {
        return projects;
    }

}
