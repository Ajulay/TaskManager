package com.ajulay.command;

import com.ajulay.endpoint.Session;

public class SessionDeleteCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/dssn";
    }

    @Override
    public String getDescription() {
        return "delete session.";
    }

    @Override
    public void execute() {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter session id:");
        final String sessionId = getController().nextLine();
        getController().getSessionService().getSessionSoapEndPointPort().deleteSessionById(session, sessionId);
    }

}
