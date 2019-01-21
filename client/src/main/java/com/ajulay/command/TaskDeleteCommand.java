package com.ajulay.command;


import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;

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
        final Session session = getController().getCurrentSession();
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final Success success = getController().getTaskService().getTaskSoapEndPointPort().deleteTask(session, taskId);
        if (success == null) {
            System.out.println("No delete.");
            return;
        }
        System.out.println("task deleted...");
    }

}
