package com.ajulay.command;

import com.ajulay.controller.ControllerUI;
import com.ajulay.entity.Task;

public class TaskFindAllCommand extends AbstractCommand {

    public static final String COMMAND = "/tks";

    public TaskFindAllCommand(ControllerUI controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "show all tasks";
    }

    @Override
    public void execute() {
        int index = 1;
        for (Task task : getController().getTaskService().getTasks()) {
            System.out.println(index++ + ". Task term: " + task.getTerm() + ", task id: " + task.getId() +
                    ", task content: " + task.getContent() + ", status: " + task.getStatus().toString());
        }
    }

}
