package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import org.jetbrains.annotations.Nullable;

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

    public void execute(@Observes @Nullable TaskDeleteCommand taskDeleteCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session session = controller.getCurrentSession();
        if (session == null) {
            System.out.println("You are log out.");
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
        @Nullable final Success success = getController().getTaskService().getTaskSoapEndPointPort().deleteTask(session, taskId);
        if (success == null) {
            System.out.println("Task is not deleted.");
            toBegin();
            return;
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
