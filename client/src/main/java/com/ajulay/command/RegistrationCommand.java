package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;

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

    @Override
    public void execute() {
        System.out.println("Enter login (required):");
        final String login = getController().nextLine();
        for (int j = 1; j <= ServiceConstant.MAX_ATTEMPT; j++) {
            if (j == ServiceConstant.MAX_ATTEMPT) {
                System.out.println("Your attempts are ended.");
                return;
            }
            System.out.println("Enter password (required):");
            final String passwordHash = getController().nextLine().hashCode() + "";
            System.out.println("Confirm password (required):");
            final String confirmedPasswordHash = getController().nextLine().hashCode() + "";
            if (!passwordHash.equals(confirmedPasswordHash)) {
                System.out.println("passwords are not equals");
                continue;
            }
            getController().getSessionService().getSessionSoapEndPointPort().register(login, passwordHash);
            return;
        }
    }

}
