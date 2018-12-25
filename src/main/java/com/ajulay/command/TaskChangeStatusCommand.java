package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

import java.util.Scanner;

public class TaskChangeStatusCommand extends AbstractCommand {

    public static final String COMMAND = "/st";

    public TaskChangeStatusCommand(ControllerUI controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
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
