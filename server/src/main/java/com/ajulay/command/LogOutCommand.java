package com.ajulay.command;

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
        getController().getSessionService().setCurrentSession(null);
        getController().getUserService().setCurrentUser(null);
    }
}
