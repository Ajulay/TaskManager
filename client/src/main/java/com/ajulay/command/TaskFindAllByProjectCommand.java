package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
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

    public void execute(@Observes TaskFindAllByProjectCommand taskFindAllByProjectCommand) {
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
        System.out.println("Enter project id...");
        @Nullable final String projectId = controller.nextLine();
        if (projectId == null || projectId.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        @NotNull final List<TaskView> tasks = controller.getTaskService().getTaskSoapEndPointPort()
                .findTaskAllByProject(session, projectId);
        int index = 1;
        for (@NotNull final TaskView task : tasks) {
            @NotNull final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
