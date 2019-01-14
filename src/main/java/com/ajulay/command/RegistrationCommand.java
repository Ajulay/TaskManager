package com.ajulay.command;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;

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
        final User user = new User();
        for (int i = 1; i <= ServiceConstant.MAX_ATTEMPT; i++) {
            if (i == ServiceConstant.MAX_ATTEMPT) {
                System.out.println("Your attempts are ended.");
                return;
            }
            System.out.println("Enter login (required):");
            final String login = getController().nextLine();
            user.setLogin(login);
            for (int j = 1; j <= ServiceConstant.MAX_ATTEMPT; j++) {
                if (j == ServiceConstant.MAX_ATTEMPT) {
                    System.out.println("Your attempts are ended.");
                    return;
                }
                System.out.println("Enter password (required):");
                final String password = getController().nextLine().hashCode() + "";
                System.out.println("Confirm password (required):");
                final String confirmedPassword = getController().nextLine().hashCode() + "";
                if (!password.equals(confirmedPassword)) {
                    System.out.println("passwords are not equals");
                    continue;
                }
                user.setPassword(confirmedPassword);
                break;
            }
            System.out.println("Enter surname (required):");
            final String surname = getController().nextLine();
            user.setSurname(surname);
            System.out.println("Enter role (required): manager or worker");
            final String sRole = getController().nextLine();
            try {
                final Role role = Role.valueOf(sRole.toUpperCase());
                user.setRole(role);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            getController().getUserService().getUsers().add(user);
            break;
        }
    }

}
