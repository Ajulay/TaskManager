package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;

import java.util.Scanner;

public class DeleteTaskCommand extends AbstractCommand {

    public DeleteTaskCommand(UIController uiController) {
        super(uiController);
    }

    @Override
    public String inputCommand() {
        return TaskConstant.DELETE_TASK;
    }

    @Override
    public String getDescription() {
        return "delete task";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter task id");
        final Scanner in = new Scanner(System.in);
        getController().getTaskService().deleteTask(in.nextLine());
    }
}
