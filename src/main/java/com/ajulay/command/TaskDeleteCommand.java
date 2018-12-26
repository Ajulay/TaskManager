package com.ajulay.command;

import java.util.Scanner;

public class TaskDeleteCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
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
        System.out.println("task deleted...");
    }

}
