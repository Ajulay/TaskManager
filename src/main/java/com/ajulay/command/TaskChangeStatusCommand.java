package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;

import java.util.List;
import java.util.stream.Collectors;

public class TaskChangeStatusCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/st";
    }

    @Override
    public String getDescription() {
        return "change status";
    }

    @Override
    public void execute() throws NoSuchTaskException, NoSuchProjectException {
        System.out.println("Enter task id:");
        final String taskId = getController().nextLine();
        final User user = getController().getCurrentUser();
        final Task task = getController().getTaskService().findTaskById(taskId);
        if (Role.MANAGER.equals(user.getRole())) {
            final Project project = getController().getProjectService().getById(task.getProjectId());
            if (!user.getId().equals(project.getAuthorId())) {
                throw new NoSuchTaskException("Manager can change task status of his projects only");
            }
        }
        if (Role.WORKER.equals(user.getRole())) {
            List<Assignee> assignees = getController().getAssigneeService().findAllAssignee();
            assignees = assignees.stream().filter(a -> a.getAssignerId().equals(user.getId()) && a.getTaskId().equals(taskId))
                    .collect(Collectors.toList());
            if (assignees.size() == 0) {
                System.out.println("Worker can change status of his task only");
                return;
            }
        }
        System.out.println("Enter task status: 'finished', 'failed' OR 'in_progress'");
        String status = getController().nextLine();
        getController().getTaskService().changeStatus(taskId, status);
        System.out.println("Status changed");
    }

}