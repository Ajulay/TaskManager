package com.ajulay.command;

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
        final String sessionId = getController().nextLine();
        getController().getSessionService().removeById(sessionId);
    }

}
