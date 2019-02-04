package com.ajulay.command;

import com.ajulay.api.IController;
import com.ajulay.endpoint.Session;
import com.ajulay.eventhandler.interseptor.Logable;
import org.jetbrains.annotations.Nullable;

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

    @Logable
    public void execute(@Observes BeginCommand beginCommand) throws Exception {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session currentSession = controller.getCurrentSession();
        if (currentSession == null) {
            controller.getCommands().clear();
            controller.register(
                    LoginCommand.class,
                    RegistrationCommand.class,
                    AppExitCommand.class, AppHelpCommand.class
            );
        } else {
            @Nullable final String currentUserName = currentSession.getUserId();
            System.out.printf("User id: %s\n", currentUserName);

        }
        @Nullable final String in = controller.nextLine();
        if (in == null || in.isEmpty()) {
            toBegin();
            return;
        }
        @Nullable final AbstractCommand command = controller.getCommands().get(in);
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
