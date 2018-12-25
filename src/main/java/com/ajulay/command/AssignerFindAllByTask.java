package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignerFindAllByTask extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/atks";
    }

    @Override
    public String getDescription() {
        return "list of assigners by task";
    }

    @Override
    public void execute() throws Exception {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter task id");
        final String taskId = sc.nextLine();
        final List<Assignee> assignees = getController().getAssigneeService().AssigneeFindAll();
        final List<Assigner> assigners = new ArrayList<>();
        final Task task = getController().getTaskService().getTaskById(taskId);
        for (Assignee assignee : assignees) {
            if (assignee.getTaskId().equals(taskId)) {
                Assigner assigner = getController().getAssignerService().findById(assignee.getAssignerId());
                assigners.add(assigner);
            }
        }
        System.out.println("Task content:\n" + task.getContent());
        int index = 1;
        for (Assigner aser : assigners) {
            System.out.println(index++ + ". Surname: " + aser.getSurname());
        }
    }

}
