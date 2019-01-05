package com.ajulay.command;

import com.ajulay.entity.Project;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;

import java.util.ArrayList;
import java.util.List;

public class ProjectFindAllCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/pts";
    }

    @Override
    public String getDescription() {
        return "show all projects";
    }

    @Override
    public void execute() {
        int index = 1;
        List<Project> userProjects = new ArrayList<>();
        User currentUser = getController().getCurrentUser();

        if (Role.ADMIN.equals(currentUser.getRole())) {
            userProjects = getController().getProjectService().getProjects();
        } else {
            for (Project project : getController().getProjectService().getProjects()) {
                if (project.getAuthorId().equals(currentUser.getId())) {
                    userProjects.add(project);
                }
            }

        }
        for (Project project : userProjects) {
            System.out.println(index++ + ". Project name: " + project.getName() + ", project id: " + project.getId() +
                    ", project author id: " + project.getAuthorId());
        }
    }

}
