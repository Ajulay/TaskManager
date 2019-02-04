package com.ajulay.command;

import com.ajulay.endpoint.Project;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;
import com.ajulay.endpoint.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
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

    public void execute(@Observes UserFindAllByTaskCommand userFindAllByTaskCommand) {
        @Nullable final Session session = getController().getCurrentSession();
        if (session == null) {
            System.out.println("You are not login.");
            toBegin();
            return;
        }
        System.out.println("Enter task id");
        @Nullable final String taskId = getController().nextLine();
        if (taskId == null || taskId.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        @Nullable final TaskView task = getController().getTaskService().getTaskSoapEndPointPort().findTaskById(session, taskId);
        if (task == null) {
            System.out.println("No such task.");
            toBegin();
            return;
        }
        @Nullable final Project project = getController().getProjectService().getProjectSoapEndPointPort().getById(session, task.getProjectId());
        if (project == null) {
            System.out.println("No project for your task.");
            toBegin();
            return;
        }
        if (!project.getAuthorId().equals(session.getUserId())) {
            System.out.println("You can see executor(s) of your projects only.");
            toBegin();
            return;
        }
        @NotNull final List<User> users = getController().getUserService().getUserSoapEndPointPort().findUserAllByTaskId(session, taskId);
        System.out.println("Task content:\n" + task.getContent());
        int index = 1;
        for (@NotNull final User user : users) {
            System.out.println(index++ + ". Surname: " + user.getSurname());
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
