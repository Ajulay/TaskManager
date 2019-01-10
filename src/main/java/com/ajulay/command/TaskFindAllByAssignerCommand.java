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

public class TaskFindAllByAssignerCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/tass";
    }

    @Override
    public String getDescription() {
        return "task list by assigner id";
    }

    @Override
    public void execute() throws NoSuchAssignerException, NoSuchTaskException, NoSuchProjectException {
        final List<Task> tasks = new ArrayList<>();
        final List<Assignee> assignees = getController().getAssigneeService().findAllAssignee();
        final User currentUser = getController().getUserService().getCurrentUser();
        System.out.println("Enter user id");
        final String userId = getController().nextLine();
        final User user = getController().getUserService().findById(userId);
        if (Role.MANAGER.equals(currentUser.getRole())) {
            for (final Assignee assignee : assignees) {
                if (assignee.getAssignerId().equals(userId)) {
                    final Task task = getController().getTaskService().findTaskById(assignee.getTaskId());
                    final Project project = getController().getProjectService().getById(task.getProjectId());
                    if (project.getAuthorId().equals(currentUser.getId())) {
                        tasks.add(task);
                    }
                }
            }
        } else {
            for (final Assignee assignee : assignees) {
                if (assignee.getAssignerId().equals(userId)) {
                    final Task task = getController().getTaskService().findTaskById(assignee.getTaskId());
                    tasks.add(task);
                }
            }
        }
        System.out.println("Executor surname: " + user.getSurname());
        int index = 1;
        for (Task task : tasks) {
            final String term = task.getTerm().toString().substring(0, 10);
            System.out.println(index++ + ". Task content: " + task.getContent() + ", term: " + term);
        }
    }

}
