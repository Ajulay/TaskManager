package com.ajulay.command;

import com.ajulay.entity.Session;

public class SessionAllShowCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/ssn";
    }

    @Override
    public String getDescription() {
        return "show sessions";
    }

    @Override
    public void execute() {
        int index = 1;
        for (final Session session : getController().getSessionService().findAll()) {
            System.out.println(index++ + ". session id: " + session.getId() + ", signature: " + session.getSignature() +
                    ", user: " + session.getUserId());
        }
    }

}
