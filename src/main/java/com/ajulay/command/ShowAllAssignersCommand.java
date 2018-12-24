package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;

public class ShowAllAssignersCommand extends AbstractCommand {

    public ShowAllAssignersCommand(UIController controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return TaskConstant.SHOW_ASSIGNERS_COMMAND;
    }

    @Override
    public String getDescription() {
        return "Show all executors";
    }

    @Override
    public void execute() {
        getController().getAssignerService().showAssigners();
    }

}
