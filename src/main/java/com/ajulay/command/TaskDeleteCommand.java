package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

import java.util.Scanner;

public class TaskDeleteCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/dt";
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
