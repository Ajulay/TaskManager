package com.ajulay.command;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.Task;

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

    @Override
    public void execute() {
        final String currentUserId = getController().getSessionService().getCurrentSession().getUserId();
        final List<Task> tasks = getController().getTaskService().findTaskAllByUserId(currentUserId);
        int index = 1;
        for (final Task task : tasks) {
            final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent() + ", status: " + task.getStatus().toString());
        }
    }

}
