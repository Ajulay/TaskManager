package com.ajulay.command;

import com.ajulay.entity.User;

public class ChangePasswordCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/chpwd";
    }

    @Override
    public String getDescription() {
        return "change password";
    }

    @Override
    public void execute() {
        final String newPassword = getController().nextLine();
        final User currentUser = getController().getUserService().getCurrentUser();
        currentUser.setPassword(newPassword.hashCode() + "");
    }
}
