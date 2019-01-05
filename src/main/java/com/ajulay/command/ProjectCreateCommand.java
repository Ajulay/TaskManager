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
        final Project project = new Project();
        project.setAuthorId(getController().getCurrentUser().getId());
        project.setName(projectName);
        getController().getProjectService().saveProject(project);
    }

}
