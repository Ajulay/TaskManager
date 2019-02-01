package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.User;

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
        final Session session = getController().getCurrentSession();
        int index = 1;
        final List<User> users = getController().getUserService().getUserSoapEndPointPort().getUsers(session);
        for (final User user : users) {
            System.out.println(index++ + ". Surname: " + user.getSurname() + ", id: " + user.getId() + ", role: " +
                    user.getRole());
        }
    }

}