package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.User;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;

public class SessionAllShowCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/ssn";
    }

    @Override
    public String getDescription() {
        return "show sessions";
    }


    public void execute(@Observes SessionAllShowCommand sessionAllShowCommand) {
        @Nullable final Session currentSession = getController().getCurrentSession();
        if (currentSession == null) {
            System.out.println("You are not login");
            toBegin();
            return;
        }
        @Nullable final User currentUser = getController().getUserService().getUserSoapEndPointPort()
                .findById(currentSession, currentSession.getUserId());
        if (currentUser == null) {
            System.out.println("You are not admin");
            toBegin();
            return;
        }
        int index = 1;
        for (Session session : getController().getSessionService().getSessionSoapEndPointPort().getSessionAll(currentSession)) {
            System.out.println(index++ + ". session id: " + session.getId() + ", signature: " + session.getSignature() +
                    ", user: " + session.getUserId());
        }
        toBegin();
    }

}
