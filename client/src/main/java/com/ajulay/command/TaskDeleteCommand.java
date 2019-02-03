package com.ajulay.command;


import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;

import javax.enterprise.event.Observes;

public class TaskDeleteCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/dt";
    }

    @Override
    public String getDescription() {
        return "delete task";
    }

    public void execute(@Observes TaskDeleteCommand taskDeleteCommand) {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final Success success = getController().getTaskService().getTaskSoapEndPointPort().deleteTask(session, taskId);
        final String result = success == null ? "No delete." : "task deleted...";
        System.out.println(result);
        System.out.println("[Ok]");
        toBegin();
    }

}
