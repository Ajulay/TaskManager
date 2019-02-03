package com.ajulay.command;

import com.ajulay.endpoint.Session;

import javax.enterprise.event.Observes;

public class SessionDeleteCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/dssn";
    }

    @Override
    public String getDescription() {
        return "delete session.";
    }

    public void execute(@Observes SessionDeleteCommand sessionDeleteCommand) {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter session id:");
        final String sessionId = getController().nextLine();
        getController().getSessionService().getSessionSoapEndPointPort().deleteSessionById(session, sessionId);
    }

}
