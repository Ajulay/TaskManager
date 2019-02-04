package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;

public class LogOutCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/out";
    }

    @Override
    public String getDescription() {
        return "log out";
    }

    public void execute(@Observes LogOutCommand logOutCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session session = getController().getCurrentSession();
        if (session == null) {
            toBegin();
            return;
        }
        final Success success = getController().getSessionService().getSessionSoapEndPointPort().logout(session);
        if (success == null) {
            System.out.println("Check connect.");
            toBegin();
            return;
        }
        controller.setCurrentSession(null);
        System.out.println("You are logout.");
        System.out.println("[Ok]");
        toBegin();
    }

}
