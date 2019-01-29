package com.ajulay.command;

import com.ajulay.endpoint.Session;

public class AppExitCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/exit";
    }

    @Override
    public String getDescription() {
        return "Bye!";
    }

    @Override
    public void execute() {
        final Session session = getController().getCurrentSession();
        getController().getSessionService().getSessionSoapEndPointPort().logout(session);
        System.exit(0);
    }

}
