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
    public void execute() throws Exception {
        User user = new User();
        while (true) {
            System.out.println("Enter login (required):");
            final String login = getController().nextLine();
            if (!getController().getUserService().isLoginExists(login)) {
                user.setLogin(login);
                String confirmedPassword = null;
                while (true) {
                    System.out.println("Enter password (required):");
                    final String password = getController().nextLine();
                    System.out.println("Confirm password (required):");
                    confirmedPassword = getController().nextLine();
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
    }

}
