package com.ajulay.command;

import com.ajulay.controller.ControllerUI;
import com.ajulay.entity.Assigner;

public class AssignerFindAllCommand extends AbstractCommand {

    public static final String COMMAND = "/exs";

    public AssignerFindAllCommand(ControllerUI controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "show all executors";
    }

    @Override
    public void execute() {
        int index = 1;
        for (Assigner assigner : getController().getAssignerService().getAssigners()) {
            System.out.println(index++ + ". Surname: " + assigner.getSurname() + ", id: " + assigner.getId());
        }
    }

}
