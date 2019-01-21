package com.ajulay.command;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Task;

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

    @Override
    public void execute() {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter user id:");
        String userId = getController().nextLine();
        final List<Task> tasks = getController().getTaskService().getTaskSoapEndPointPort()
                .findTaskAllByUserId(session, userId);
        int index = 1;
        for (final Task task : tasks) {
            final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task content: " + task.getContent() + ", term: " + term);
        }
    }

}
