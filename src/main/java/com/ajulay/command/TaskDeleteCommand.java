package com.ajulay.command;

import com.ajulay.exception.checked.NoSuchTaskException;

public class TaskDeleteCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/dt";
    }

    @Override
    public String getDescription() {
        return "delete task";
    }

    @Override
    public void execute() throws NoSuchTaskException {
        System.out.println("Enter task id");
        getController().getTaskService().deleteTask(getController().nextLine());
        System.out.println("task deleted...");
    }

}
