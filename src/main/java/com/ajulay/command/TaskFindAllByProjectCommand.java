package com.ajulay.command;

import com.ajulay.controller.ControllerUI;
import com.ajulay.entity.Task;

import java.util.Scanner;

public class TaskFindAllByProjectCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/ptks";
    }

    @Override
    public String getDescription() {
        return "show tasks in project";
    }

    @Override
    public void execute() {
        final Scanner in = new Scanner(System.in);
        System.out.println("Enter project id...");
        final String projectId = in.nextLine();
        int index = 1;
        System.out.println("Project id: " + projectId);
        for (Task task : getController().getTaskService().getTasksByProject(projectId)) {
            System.out.println(index++ + ". Task term: " + task.getTerm() + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
    }

}
