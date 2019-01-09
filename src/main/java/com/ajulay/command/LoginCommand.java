package com.ajulay.command;

import com.ajulay.entity.User;

public class LoginCommand extends AbstractCommand {

    public final static String COMMAND = "/login";

    @Override
    public String getCommandKeyWord() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "login";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter login");
        final String login = getController().nextLine();

        User user = getController().getUserService().findByLogin(login);
        System.out.println("Enter password");
        final String password = getController().nextLine();
        if (!user.getPassword().equals(password)) {
            throw new Exception("incorrect password");
        }
        getController().setCurrentUser(user);
    }

}
