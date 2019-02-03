package com.ajulay.command;

import com.ajulay.endpoint.Session;

import javax.enterprise.event.Observes;

public class AppExitCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/exit";
    }

    @Override
    public String getDescription() {
        return "Bye!";
    }

    public void execute(@Observes AppExitCommand appExitCommand) {
        final Session session = getController().getCurrentSession();
        if (session != null) {
            getController().getSessionService().getSessionSoapEndPointPort().logout(session);
        }
    }

}
