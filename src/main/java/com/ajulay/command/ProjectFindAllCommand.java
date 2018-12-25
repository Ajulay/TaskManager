package com.ajulay.command;

import com.ajulay.entity.Project;

public class ProjectFindAllCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/pts";
    }

    @Override
    public String getDescription() {
        return "show all projects";
    }

    @Override
    public void execute() {
        int index = 1;
        for (Project project : getController().getProjectService().getProjects()) {
            System.out.println(index++ + ". Project name: " + project.getName() + ", project id: " + project.getId());
        }
    }
}
