package com.ajulay.command;

import com.ajulay.entity.Task;

public class TaskFindAllCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/tks";
    }

    @Override
    public String getDescription() {
        return "show all tasks";
    }

    @Override
    public void execute() {
        int index = 1;
        for (Task task : getController().getTaskService().findTaskAll()) {
            System.out.println(index++ + ". Task term: " + task.getTerm() + ", task id: " + task.getId() +
                    ", task content: " + task.getContent() + ", status: " + task.getStatus().toString());
        }
    }

}
