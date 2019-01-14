package com.ajulay.command;

import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;

import java.util.List;

public class UserFindAllByTaskCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/atks";
    }

    @Override
    public String getDescription() {
        return "list of assigners by task";
    }

    @Override
    public void execute() throws NoSuchTaskException, NoSuchAssignerException, NoSuchProjectException {
        System.out.println("Enter task id");
        final String taskId = getController().nextLine();
        final Task task = getController().getTaskService().findTaskById(taskId);
        final List<User> users = getController().getOveralService().findUserAllByTaskId(taskId);
        System.out.println("Task content:\n" + task.getContent());
        int index = 1;
        for (final User user : users) {
            System.out.println(index++ + ". Surname: " + user.getSurname());
        }
    }

}
