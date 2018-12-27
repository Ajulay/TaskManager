package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Task;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchTaskException;

import java.util.ArrayList;
import java.util.List;

public class AssignerFindAllByTask extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/atks";
    }

    @Override
    public String getDescription() {
        return "list of assigners by task";
    }

    @Override
    public void execute() throws NoSuchTaskException, NoSuchAssignerException {
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final List<Assignee> assignees = getController().getAssigneeService().AssigneeFindAll();
        final List<Assigner> assigners = new ArrayList<>();
        final Task task = getController().getTaskService().findTaskById(taskId);
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
