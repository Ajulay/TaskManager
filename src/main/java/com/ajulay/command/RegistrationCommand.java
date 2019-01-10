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
            if (getController().getUserService().isLoginExists(login)) {
                System.out.println("This login already exists.");
                continue;
            }
                user.setLogin(login);
            for (int j = 0; j < 3; j++) {
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
            if (user.getPassword() == null) {
                System.out.println("Keep attention when enter passwords");
                continue;
            }
                System.out.println("Enter surname (required):");
                final String surname = getController().nextLine();
            if (surname != null) user.setSurname(surname);
                System.out.println("Enter role (required): manager or worker");
                final String role = getController().nextLine();
            if (role != null) user.setRole(Role.valueOf(role.toUpperCase()));
                getController().getUserService().getUsers().add(user);
                return;
        }
        System.out.println("You try to enter incorrect data during registration.");
        System.exit(0);
    }

}
