package com.ajulay.command;

import com.ajulay.entity.Project;

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
        final String currentUserId = getController().getSessionService().getCurrentSession().getUserId();
        final List<Project> userProjects = getController().getProjectService().findAllByUserId(currentUserId);
        for (final Project project : userProjects) {
            System.out.println(index++ + ". Project name: " + project.getName() + ", project id: " + project.getId() +
                    ", project author id: " + project.getAuthorId());
        }
    }

}
