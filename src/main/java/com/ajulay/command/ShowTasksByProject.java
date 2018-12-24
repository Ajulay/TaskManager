package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;

public class ShowTasksByProject extends AbstractCommand {

    public ShowTasksByProject(UIController uiController) {
        super(uiController);
    }

    @Override
    public String inputCommand() {
        return TaskConstant.PROJECT_TASKS;
    }

    @Override
    public String getDescription() {
        return "show tasks in project";
    }

    @Override
    public void execute() throws Exception {
        final String project_id = getController().getTaskData()[0];
        getController().getTaskService().showTasksByProject(project_id);
    }
}
