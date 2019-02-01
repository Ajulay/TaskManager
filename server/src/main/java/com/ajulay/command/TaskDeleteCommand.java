package com.ajulay.command;

import com.ajulay.entity.Project;
import com.ajulay.entity.Task;

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
    public void execute() {
        final String currentUserId = getController().getSessionService().getCurrentSession().getUserId();
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final Task task = getController().getTaskService().findById(taskId);
        if (task == null) {
            System.out.println("No such task.");
            return;
        }
        final Project project = getController().getProjectService().findById(task.getProject().getId());
        if (project == null) {
            getController().getTaskService().removeById(taskId);
            getController().getAssigneeService().removeAllByTaskId(taskId);
            return;
        }
        if (!currentUserId.equals(project.getAuthorId())) {
            System.out.println("You can deleted tasks of your project only.");
            return;
        }
        getController().getTaskService().removeById(taskId);
        getController().getAssigneeService().removeAllByTaskId(taskId);
        System.out.println("task deleted...");
    }

}
