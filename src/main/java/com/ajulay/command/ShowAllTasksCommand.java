package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;

import java.util.Map;

public class ShowAllTasksCommand extends AbstractCommand {

    public ShowAllTasksCommand(UIController controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
    return TaskConstant.SHOW_TASKS_COMMAND;}

    @Override
    public String getDescription() {
        return "Show all tasks";
    }

    @Override
    public void execute() {
        getController().getTaskService().showTasks();
    }

}
