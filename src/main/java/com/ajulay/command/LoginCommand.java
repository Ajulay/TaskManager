package com.ajulay.command;

import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;

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
        for (int i = 0; i < 3; i++) {
            System.out.println("type /login");
            final String login = getController().nextLine();
            final User user = getController().getUserService().findByLogin(login);
            if (user == null) {
                System.out.println("No such user");
                continue;
            }
            System.out.println("Enter password");
            final String passwordHash = getController().nextLine().hashCode() + "";
            if (!user.getPassword().equals(passwordHash)) {
                System.out.println("Incorrect password");
                continue;
            }
            getController().getUserService().setCurrentUser(user);
            break;
        }
        if (getController().getUserService().getCurrentUser() == null) {
            System.out.println("You entered incorrect data more than 3 times.");
            return;
        }
        getController().getCommands().clear();
        if (Role.WORKER.equals(getController().getUserService().getCurrentUser().getRole())) {
            getController().registerBaseCommandAll();
        } else {
            getController().registerCommandAll();
        }
    }

}
