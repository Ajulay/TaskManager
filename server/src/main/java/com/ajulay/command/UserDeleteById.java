package com.ajulay.command;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.User;

public class UserDeleteById extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/dusr";
    }

    @Override
    public String getDescription() {
        return "delete user by id.";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter user id:");
        final String userId = getController().nextLine();
        final User user = getController().getUserService().removeById(userId);
        System.out.println("User with id: " + user.getId() + " deleted.");
        if (ServiceConstant.ADMIN.equals(user.getLogin())) {
            getController().getCommands().get(LogOutCommand.COMMAND).execute();
        }
    }

}
