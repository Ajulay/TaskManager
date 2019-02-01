package com.ajulay.command;

import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;

import java.util.List;

public class UserFindAllByTaskCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/atks";
    }

    @Override
    public String getDescription() {
        return "list of assigners by task";
    }

    @Override
    public void execute() {
        final String currentUserId = getController().getSessionService().getCurrentSession().getUserId();
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final Task task = getController().getTaskService().findById(taskId);
        final Project project = getController().getProjectService().findById(task.getProject().getId());
        if (project == null) {
            System.out.println("No project for your task.");
            return;
        }
        if (!project.getAuthorId().equals(currentUserId)) {
            System.out.println("You can see executor(s) of your projects only.");
            return;
        }
        final List<User> users = getController().getUserService().findUserAllByTaskId(taskId);
        System.out.println("Task content:\n" + task.getContent());
        int index = 1;
        for (final User user : users) {
            System.out.println(index++ + ". Surname: " + user.getSurname());
        }
    }

}
