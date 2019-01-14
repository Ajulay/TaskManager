package com.ajulay.command;

import com.ajulay.entity.Project;

public class ProjectCreateCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/pcrt";
    }

    @Override
    public String getDescription() {
        return "create project";
    }

    @Override
    public void execute() {
        System.out.println("Enter project name");
        final String projectName = getController().nextLine();
        final Project project = getController().getOveralService().createProjectByName(projectName);
        if (project == null) {
            System.out.println("Project not created...");
        }
    }

}
