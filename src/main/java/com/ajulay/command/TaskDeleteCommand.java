package com.ajulay.command;

import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;

import java.util.Scanner;

public class TaskDeleteCommand extends AbstractCommand {

    public static final String COMMAND = "/dt";

    public TaskDeleteCommand(UIController uiController) {
        super(uiController);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
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