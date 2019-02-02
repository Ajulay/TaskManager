package com.ajulay.command;

import com.ajulay.entity.Session;

import java.util.List;

public class SessionFindByIdCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/ssid";
    }

    @Override
    public String getDescription() {
        return "find session by user id";
    }

    @Override
    public void execute() {
        System.out.println("Enter user id:");
        final String userId = getController().nextLine();
        final List<Session> sessions = getController().getSessionService().findByUserId(userId);
        for (final Session session : sessions) {
            System.out.println("Session: " + session.getSignature());
        }

    }

}