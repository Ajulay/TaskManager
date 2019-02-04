package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.constants.ServiceConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;

public class RegistrationCommand extends AbstractCommand {

    public final static String COMMAND = "/reg";

    @Override
    public String getCommandKeyWord() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "registration new user";
    }

    public void execute(@Observes @Nullable RegistrationCommand registrationCommand) {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        System.out.println("Enter login (required):");
        @Nullable final String login = controller.nextLine();
        if (login == null || login.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        for (int j = 1; j <= ServiceConstant.MAX_ATTEMPT; j++) {
            if (j == ServiceConstant.MAX_ATTEMPT) {
                System.out.println("Your attempts are ended.");
                break;
            }
            System.out.println("Enter password (required):");
            @NotNull final String passwordHash = getController().nextLine().hashCode() + "";
            System.out.println("Confirm password (required):");
            @NotNull final String confirmedPasswordHash = getController().nextLine().hashCode() + "";
            if (!passwordHash.equals(confirmedPasswordHash)) {
                System.out.println("passwords are not equals");
                continue;
            }
            controller.getSessionService().getSessionSoapEndPointPort().register(login, passwordHash);
            break;
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
