package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
import java.util.List;

public class TaskFindAllByUserCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/tass";
    }

    @Override
    public String getDescription() {
        return "task list by assigner id";
    }

    public void execute(@Observes @Nullable TaskFindAllByUserCommand taskFindAllByUserCommand) {
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
        System.out.println("Enter user id:");
        @Nullable final String userId = controller.nextLine();
        if (userId == null || userId.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        @NotNull final List<TaskView> tasks = getController().getTaskService().getTaskSoapEndPointPort()
                .findTaskAllByUserId(session, userId);
        int index = 1;
        for (@NotNull final TaskView task : tasks) {
            @NotNull final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task content: " + task.getContent() + ", term: " + term);
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
