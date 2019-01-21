package com.ajulay.command;


import com.ajulay.endpoint.Project;
import com.ajulay.endpoint.Session;

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
        final Session session = getController().getCurrentSession();
        final List<Project> userProjects = getController().getProjectService().getProjectSoapEndPointPort().getProjects(session);
        for (final Project project : userProjects) {
            System.out.println(index++ + ". Project name: " + project.getName() + ", project id: " + project.getId() +
                    ", project author id: " + project.getAuthorId());
        }
    }

}
