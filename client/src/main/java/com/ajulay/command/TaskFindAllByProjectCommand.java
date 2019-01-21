package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Task;

import java.util.List;

public class TaskFindAllByProjectCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/ptks";
    }

    @Override
    public String getDescription() {
        return "show tasks in project";
    }

    @Override
    public void execute() {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter project id...");
        final String projectId = getController().nextLine();
        final List<Task> tasks = getController().getTaskService().getTaskSoapEndPointPort()
                .findTaskAllByProject(session, projectId);
        int index = 1;
        for (final Task task : tasks) {
            final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
    }

}
