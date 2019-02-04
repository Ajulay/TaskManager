package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.User;
import org.jetbrains.annotations.NotNull;
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


    public void execute(@Observes @Nullable SessionAllShowCommand sessionAllShowCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session currentSession = controller.getCurrentSession();
        if (currentSession == null) {
            System.out.println("You are not login");
            toBegin();
            return;
        }
        @Nullable final User currentUser = controller.getUserService().getUserSoapEndPointPort()
                .findById(currentSession, currentSession.getUserId());
        if (currentUser == null) {
            toBegin();
            return;
        }
        int index = 1;
        for (@NotNull final Session session : controller.getSessionService().getSessionSoapEndPointPort().getSessionAll(currentSession)) {
            System.out.println(index++ + ". session id: " + session.getId() + ", signature: " + session.getSignature() +
                    ", user: " + session.getUserId());
        }
        toBegin();
    }

}
