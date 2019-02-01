package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;

import java.util.List;

public class TaskFindAllCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/tks";
    }

    @Override
    public String getDescription() {
        return "show all tasks";
    }

    @Override
    public void execute() {
        final Session session = getController().getCurrentSession();
        final List<TaskView> tasks = getController().getTaskService().getTaskSoapEndPointPort().findTaskAllByUserId(session, session.getUserId());
        int index = 1;
        for (final TaskView task : tasks) {
            final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent() + ", status: " + (task.getStatus() == null ? "no defined" : task.getStatus().toString()));
        }
    }

}
