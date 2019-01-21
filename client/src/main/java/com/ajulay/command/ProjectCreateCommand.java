package com.ajulay.command;


import com.ajulay.endpoint.ProjectSoapEndPointService;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;

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
        final Session session = getController().getCurrentSession();
        System.out.println("Enter project name");
        final String projectName = getController().nextLine();
        if (session == null) return;
        final ProjectSoapEndPointService projectService = getController().getProjectService();
        final Success success = projectService.getProjectSoapEndPointPort().createProjectByName(session, projectName);
        if (success == null) {
            System.out.println("Project not created...");
            return;
        }
    }

}
