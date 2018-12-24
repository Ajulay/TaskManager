package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Project;

public class AssignerFindAllCommand extends AbstractCommand {

    public static final String COMMAND = "/exs";

    public AssignerFindAllCommand(UIController controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Show all executors";
    }

    @Override
    public void execute() {
        int index = 1;
        for (Assigner assigner: getController().getAssignerService().getAssigners()) {
            System.out.println(index++ + ". Project name: " + assigner.getName() + ", project id: " + assigner.getId());
        }
    }

}
