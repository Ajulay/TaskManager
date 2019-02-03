package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;

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
        final Session session = getController().getCurrentSession();
        final Success success = getController().getSessionService().getSessionSoapEndPointPort().logout(session);
        if (success == null) {
            System.out.println("Check connect.");
            return;
        }
        getController().setCurrentSession(null);
        System.out.println("You are logout.");
    }

}
