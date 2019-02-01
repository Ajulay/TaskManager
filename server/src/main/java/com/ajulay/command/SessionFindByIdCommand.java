package com.ajulay.command;

import com.ajulay.entity.Session;

public class SessionFindByIdCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/ssid";
    }

    @Override
    public String getDescription() {
        return "find ssesion by id";
    }

    @Override
    public void execute() {
        System.out.println("Enter session id:");
        final String sessionId = getController().nextLine();
        Session session = getController().getSessionService().findById(sessionId);
        System.out.println("Session: " + session.getSignature());
    }

}