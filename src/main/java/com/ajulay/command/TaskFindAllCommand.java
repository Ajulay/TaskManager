package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Task;

import java.util.Map;

public class TaskFindAllCommand extends AbstractCommand {

    public static final String COMMAND = "/tks";

    public TaskFindAllCommand(UIController controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
    return COMMAND;}

    @Override
    public String getDescription() {
        return "Show all tasks";
    }

    @Override
    public void execute() {
        int index = 1;
        for (Task task : getController().getTaskService().getTasks()) {
            System.out.println(index++ + ". Task term: " + task.getTerm() + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
    }

}
