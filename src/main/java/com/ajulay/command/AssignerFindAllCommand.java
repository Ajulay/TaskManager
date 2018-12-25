package com.ajulay.command;

import com.ajulay.controller.ControllerUI;
import com.ajulay.entity.Assigner;

public class AssignerFindAllCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/exs";
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
