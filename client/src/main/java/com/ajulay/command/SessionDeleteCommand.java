package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;

public class SessionDeleteCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/dssn";
    }

    @Override
    public String getDescription() {
        return "delete session.";
    }

    public void execute(@Observes SessionDeleteCommand sessionDeleteCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session session = controller.getCurrentSession();
        if (session == null) {
            System.out.println("You are log out.");
            toBegin();
            return;
        }
        System.out.println("Enter session id:");
        @Nullable final String sessionId = getController().nextLine();
        if (sessionId == null || sessionId.isEmpty()) {
            System.out.println("Incorrect data...");
            toBegin();
            return;
        }
        if (sessionId.equals(controller.getCurrentSession().getId())) {
            System.out.println("You are logout...");
            toBegin();
            return;
        }
        @Nullable final Success success = controller.getSessionService().getSessionSoapEndPointPort().deleteSessionById(session, sessionId);
        if (success == null) {
            System.out.println("No delete. Try again.");
            toBegin();
            return;
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
