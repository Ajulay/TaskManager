package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;
import com.ajulay.entity.Project;

import java.util.Map;

public class ProjectFindAllCommand extends AbstractCommand {

    public static final String COMMAND = "/pts";

    public ProjectFindAllCommand(UIController controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Show all projects";
    }

    @Override
    public void execute() {
        int index = 1;
        for (Project project: getController().getProjectService().getProjects()) {
            System.out.println(index++ + ". Project name: " + project.getName() + ", project id: " + project.getId());
        }
    }
}
