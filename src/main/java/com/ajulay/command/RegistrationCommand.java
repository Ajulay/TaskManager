package com.ajulay.command;

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
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter login (required):");
            final String login = getController().nextLine();
            if (!getController().getUserService().isLoginExists(login)) {
                user.setLogin(login);
                while (true) {
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
                final String role = getController().nextLine();
                user.setRole(Role.valueOf(role.toUpperCase()));
                getController().getUserService().getUsers().add(user);
                getController().setCurrentUser(user);
                return;
            }
            System.out.println("This login already exists.");
        }
        System.out.println("You try to enter incorrect data during registration.");
    }

}
