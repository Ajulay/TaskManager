package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Task;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchTaskException;

import java.util.ArrayList;
import java.util.List;

public class TaskFindAllByAssigner extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/tass";
    }

    @Override
    public String getDescription() {
        return "task list by assigner id";
    }

    @Override
    public void execute() throws NoSuchAssignerException, NoSuchTaskException {
        System.out.println("Enter assigner id");
        final String assignerId = getController().nextLine();
        final List<Assignee> assignees = getController().getAssigneeService().findAllAssignee();
        final List<Task> tasks = new ArrayList<>();
        final Assigner assigner = getController().getAssignerService().findById(assignerId);
        for (Assignee assignee : assignees) {
            if (assignee.getAssignerId().equals(assignerId)) {
                Task task = getController().getTaskService().findTaskById(assignee.getTaskId());
                tasks.add(task);
            }
        }
        System.out.println("Executor surname: " + assigner.getSurname());
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index++ + ". Task content: " + task.getContent());
        }
    }

}
