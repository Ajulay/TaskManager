package com.ajulay.services;

import com.ajulay.projects.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private static List<Project> projects = new ArrayList<>();


   public Project getByName(String name) throws Exception {
        Project project = null;
       for (Project p: projects) {
           if(p.getName().equals(name)){
               return p;
           }
       }
       throw new Exception("No such project");
   }

   public static void showProjects(){
       for (Project project: projects) {
           System.out.println(project.getName());
       }
   }

    public static List<Project> getProjects() {
        return projects;
    }

}
