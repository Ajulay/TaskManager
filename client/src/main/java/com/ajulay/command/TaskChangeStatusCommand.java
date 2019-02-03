package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;

import javax.enterprise.event.Observes;

public class TaskChangeStatusCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/st";
    }

    @Override
    public String getDescription() {
        return "change status";
    }

    public void execute(@Observes TaskChangeStatusCommand taskChangeStatusCommand) {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter task id:");
        final String taskId = getController().nextLine();
        System.out.println("Enter task status: 'finished', 'failed' OR 'in_progress'");
        final String status = getController().nextLine();
        final Success success = getController().getTaskService().getTaskSoapEndPointPort().changeStatus(session, taskId, status);
        if (success == null) {
            System.out.println("Status not changed");
            return;
        }
        System.out.println("Status changed");
    }

}