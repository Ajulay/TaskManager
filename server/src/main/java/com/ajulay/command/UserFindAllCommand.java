package com.ajulay.command;

import com.ajulay.entity.User;

import java.util.List;

public class UserFindAllCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/exs";
    }

    @Override
    public String getDescription() {
        return "show all executors";
    }

    @Override
    public void execute() {
        int index = 1;
        final List<User> users = getController().getUserService().findAll();
        for (final User user : users) {
            System.out.println(index++ + ". Surname: " + user.getSurname() + ", id: " + user.getId() + ", role: " +
                    user.getRole());
        }
    }

}
