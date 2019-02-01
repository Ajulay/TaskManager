package com.ajulay.command;

import com.ajulay.endpoint.util.SessionSignature;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;

import java.util.Date;

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
        User user = null;
        for (int i = 0; i < 3; i++) {
            System.out.println("type /login");
            final String login = getController().nextLine();
            user = getController().getUserService().findByLogin(login);
            if (user == null) {
                System.out.println("No such user");
                continue;
            }
            System.out.println("Enter password");
            final String passwordHash = getController().nextLine().hashCode() + "";
            if (!user.getPasswordHash().equals(passwordHash)) {
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
        getController().registerCommandAll();
        final Session session = new Session();
        session.setUserId(user.getId());
        session.setSignature(SessionSignature.sign(user.getId()));
        session.setCreatedDate(new Date());
        getController().getSessionService().save(session);
        getController().getSessionService().setCurrentSession(session);
    }

}
