package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.endpoint.ProjectSoapEndPointService;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;

public class ProjectCreateCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/pcrt";
    }

    @Override
    public String getDescription() {
        return "create project";
    }

    public void execute(@Observes @Nullable ProjectCreateCommand projectCreateCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session session = getController().getCurrentSession();
        if (session == null) {
            System.out.println("You are log out.");
            toBegin();
            return;
        }
        System.out.println("Enter project name");
        @Nullable final String projectName = controller.nextLine();
        if (projectName == null || projectName.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        @NotNull final ProjectSoapEndPointService projectService = controller.getProjectService();
        @Nullable final Success success = projectService.getProjectSoapEndPointPort().createProjectByName(session, projectName);
        if (success == null) {
            System.out.println("Project not created...");
            toBegin();
            return;
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
