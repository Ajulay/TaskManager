package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;

import javax.enterprise.event.Observes;

public class BeginCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "start";
    }

    public void execute(@Observes BeginCommand beginCommand) throws Exception {
        final IController controller = getController();
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
            toBegin();
        }
        final AbstractCommand command = controller.getCommands().get(in);
        if (command != null) {
            System.out.println(command.getDescription());
            try {
                getAbstractCommandEvent().fire(command);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("You entered incorrect data... Try again.");
        toBegin();
    }

}
