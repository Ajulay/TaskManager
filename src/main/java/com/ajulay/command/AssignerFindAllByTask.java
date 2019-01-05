package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchProjectException;
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
    public void execute() throws NoSuchTaskException, NoSuchAssignerException, NoSuchProjectException {
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final User user = getController().getCurrentUser();
        final List<Assignee> assignees = getController().getAssigneeService().findAllAssignee();
        final List<User> assigners = new ArrayList<>();
        final Task task = getController().getTaskService().findTaskById(taskId);
        if (Role.MANAGER.equals(user.getRole())) {
            final Project project = getController().getProjectService().getById(task.getProjectId());
            if (!user.getId().equals(project.getAuthorId())) {
                throw new NoSuchTaskException("Manager can see task of his projects only");
            }
        }
        for (Assignee assignee : assignees) {
            if (assignee.getTaskId().equals(taskId)) {
                User assigner = getController().getAssignerService().findById(assignee.getAssignerId());
                assigners.add(assigner);
            }
        }
        System.out.println("Task content:\n" + task.getContent());
        int index = 1;
        for (User aser : assigners) {
            System.out.println(index++ + ". Surname: " + aser.getSurname());
        }
    }

}
