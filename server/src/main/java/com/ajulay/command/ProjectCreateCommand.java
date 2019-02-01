package com.ajulay.command;


import com.ajulay.api.service.IProjectService;
import com.ajulay.entity.Project;
import com.ajulay.entity.Session;

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
        final Session session = getController().getSessionService().getCurrentSession();
        if (session == null) return;
        final IProjectService projectService = getController().getProjectService();
        final Project project = projectService.createProjectByName(projectName);
        if (project == null) {
            System.out.println("Project not created...");
            return;
        }
        project.setAuthorId(session.getUserId());
        projectService.update(project); //TODO smth problem?
    }

}
