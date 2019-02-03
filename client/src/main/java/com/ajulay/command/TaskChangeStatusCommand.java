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
        String status = getController().nextLine();
        final Success success = getController().getTaskService().getTaskSoapEndPointPort().changeStatus(session, taskId, status);
        final String result = success == null ? "Status not changed" : "Status changed";
        System.out.println(result);
        System.out.println("[Ok]");
        toBegin();
    }

}