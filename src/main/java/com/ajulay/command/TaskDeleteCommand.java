package com.ajulay.command;

import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;

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
    public void execute() throws NoSuchTaskException, NoSuchProjectException {
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final User user = getController().getUserService().getCurrentUser();
        final Task task = getController().getTaskService().findTaskById(taskId);
        if (Role.MANAGER.equals(user.getRole())) {
            final Project project = getController().getProjectService().getById(task.getProjectId());
            if (!user.getId().equals(project.getAuthorId())) {
                throw new NoSuchTaskException("Manager can delete task of his projects only");
            }
        }
        getController().getTaskService().deleteTask(taskId);
        System.out.println("task deleted...");
    }

}
