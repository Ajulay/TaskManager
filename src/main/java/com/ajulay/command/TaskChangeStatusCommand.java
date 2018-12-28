package com.ajulay.command;

import com.ajulay.exception.checked.NoSuchTaskException;

public class TaskChangeStatusCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/st";
    }

    @Override
    public String getDescription() {
        return "change status";
    }

    @Override
    public void execute() throws NoSuchTaskException {
        System.out.println("Enter task id:");
        String taskId = getController().nextLine();
        System.out.println("Enter task status: 'finished', 'failed' OR 'in_progress'");
        String status = getController().nextLine();
        getController().getTaskService().changeStatus(taskId, status);
        System.out.println("Status changed");
    }

}
