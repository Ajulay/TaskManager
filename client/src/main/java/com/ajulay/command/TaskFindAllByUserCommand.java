package com.ajulay.command;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;

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

    public void execute(@Observes TaskFindAllByUserCommand taskFindAllByUserCommand) {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter user id:");
        String userId = getController().nextLine();
        final List<TaskView> tasks = getController().getTaskService().getTaskSoapEndPointPort()
                .findTaskAllByUserId(session, userId);
        int index = 1;
        for (final TaskView task : tasks) {
            final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task content: " + task.getContent() + ", term: " + term);
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
