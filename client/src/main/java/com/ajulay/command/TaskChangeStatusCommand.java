package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import org.jetbrains.annotations.Nullable;

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
        System.out.println("Enter task id:");
        @Nullable final String taskId = getController().nextLine();
        if (taskId == null || taskId.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        System.out.println("Enter task status: 'finished', 'failed' OR 'in_progress'");
        @Nullable final String status = getController().nextLine();
        if (status == null || status.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        @Nullable final Success success = getController().getTaskService().getTaskSoapEndPointPort().changeStatus(session, taskId, status);
        if (success == null) {
            System.out.println("Status not changed");
            toBegin();
            return;
        }
        System.out.println("[Ok]");
        toBegin();
    }

}