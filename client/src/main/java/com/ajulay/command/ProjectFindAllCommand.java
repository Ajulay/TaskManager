package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.endpoint.Project;
import com.ajulay.endpoint.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
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

    public void execute(@Observes @Nullable ProjectFindAllCommand projectFindAllCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        int index = 1;
        @Nullable final Session session = controller.getCurrentSession();
        if (session == null) {
            System.out.println("You are log out.");
            toBegin();
            return;
        }
        @NotNull final List<Project> userProjects = getController().getProjectService().getProjectSoapEndPointPort().getProjects(session);
        for (@NotNull final Project project : userProjects) {
            System.out.println(index++ + ". Project name: " + project.getName() + ", project id: " + project.getId() +
                    ", project author id: " + project.getAuthorId());
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
