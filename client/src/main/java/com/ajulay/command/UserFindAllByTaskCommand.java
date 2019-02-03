package com.ajulay.command;

import com.ajulay.endpoint.Project;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;
import com.ajulay.endpoint.User;

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
        final Session session = getController().getCurrentSession();
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final TaskView task = getController().getTaskService().getTaskSoapEndPointPort().findTaskById(session, taskId);
        final Project project = getController().getProjectService().getProjectSoapEndPointPort().getById(session, task.getProjectId());
        if (project == null) {
            System.out.println("No project for your task.");
            return;
        }
        if (!project.getAuthorId().equals(session.getUserId())) {
            System.out.println("You can see executor(s) of your projects only.");
            return;
        }
        final List<User> users = getController().getUserService().getUserSoapEndPointPort().findUserAllByTaskId(session, taskId);
        System.out.println("Task content:\n" + task.getContent());
        int index = 1;
        for (final User user : users) {
            System.out.println(index++ + ". Surname: " + user.getSurname());
        }
    }

}
