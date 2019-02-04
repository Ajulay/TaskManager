package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
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

    public void execute(@Observes @Nullable TaskFindAllCommand taskFindAllCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session session = getController().getCurrentSession();
        if (session == null) {
            System.out.println("You are log out.");
            toBegin();
            return;
        }
        @NotNull final List<TaskView> tasks = getController().getTaskService().getTaskSoapEndPointPort().findTaskAllByUserId(session, session.getUserId());
        int index = 1;
        for (@NotNull final TaskView task : tasks) {
            @NotNull final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent() + ", status: " + (task.getStatus() == null ? "no defined" : task.getStatus().toString()));
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
