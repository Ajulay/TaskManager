package com.ajulay.command;

import com.ajulay.entity.Session;

public class LogOutCommand extends AbstractCommand {

    public final static String COMMAND = "/out";

    @Override
    public String getCommandKeyWord() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "log out.";
    }

    @Override
    public void execute() {
        final Session session = getController().getSessionService().getCurrentSession();
        getController().getSessionService().setCurrentSession(null);
        getController().getUserService().setCurrentUser(null);
        getController().getSessionService().remove(session);
    }

}
