package com.ajulay.eventhandler;

import com.ajulay.api.IController;
import com.ajulay.command.*;
import com.ajulay.endpoint.Session;
import com.ajulay.eventhandler.interseptor.Logable;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class EventHandler {

    @Inject
    private IController controller;

    @Inject
    private Event<AbstractCommand> abstractCommandEvent;

    @Logable
    public void handle(@Observes BeginCommand begin) throws Exception {
        final Session currentSession = controller.getCurrentSession();
        if (currentSession == null) {
            controller.getCommands().clear();
            controller.register(
                    LoginCommand.class,
                    RegistrationCommand.class,
                    AppExitCommand.class, AppHelpCommand.class
            );
        } else {
            final String currentUserName = currentSession.getUserId();
            System.out.printf("User id: %s\n", currentUserName);

        }
        final String in = controller.nextLine();
        if (in == null || in.isEmpty()) {
            abstractCommandEvent.fire(begin);
            return;
        }
        final AbstractCommand command = controller.getCommands().get(in);
        if (command != null) {
            System.out.println(command.getDescription());
            try {
                abstractCommandEvent.fire(command);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return;
        }
        System.out.println("You entered incorrect data... Try again.");
    }

}
