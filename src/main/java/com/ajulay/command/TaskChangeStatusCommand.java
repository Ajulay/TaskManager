package com.ajulay.command;

import java.util.Scanner;

public class TaskChangeStatusCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/st";
    }

    @Override
    public String getDescription() {
        return "change status";
    }

    @Override
    public void execute() throws Exception {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter task id:");
        String taskId = sc.nextLine();
        System.out.println("Enter task status: 'finished', 'failed' OR 'in_working'");
        String status = sc.nextLine();
        getController().getTaskService().changeStatus(taskId, status);
        System.out.println("Status changed");
    }
}
