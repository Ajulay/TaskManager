package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;

import java.util.Map;

public class ShowAllProjectsCommand extends AbstractCommand {

    public ShowAllProjectsCommand(UIController controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return TaskConstant.SHOW_PROJECTS_COMMAND;
    }

    @Override
    public String getDescription() {
        return "Show all projects";
    }

    @Override
    public void execute() {
        getController().getProjectService().showProjects();
    }
}
