package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;

import javax.enterprise.event.Observes;
import java.util.List;

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


    public void execute(@Observes LoginCommand loginCommand) throws Exception {
        for (int i = 1; i <= ServiceConstant.MAX_ATTEMPT; i++) {
            System.out.println("Enter login:");
            final String login = getController().nextLine();
            System.out.println("Enter password:");
            final String passwordHash = getController().nextLine().hashCode() + "";
            final Session session = getController().getSessionService().getSessionSoapEndPointPort().login(login, passwordHash);
            if (session == null) {
                System.out.println("Wrong login or password. Try again.");
                continue;
            }
            getController().setCurrentSession(session);
            break;
        }
        if (getController().getCurrentSession() == null) {
            System.out.println("You entered incorrect data more than 3 times.");
            return;
        }
        final List<Session> currentSessions = getController().getSessionService().getSessionSoapEndPointPort()
                .getSessionAll(getController().getCurrentSession());
        for (final Session session : currentSessions) {
            System.out.printf("Session id: %s, signature: %s\n", session.getId(), session.getSignature());
        }
        getController().getCommands().clear();
        getController().registerCommandAll();
    }

}
