package com.ajulay.command;

public class LogOutCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/out";
    }

    @Override
    public String getDescription() {
        return "log out from application";
    }

    @Override
    public void execute() {
        getController().getUserService().setCurrentUser(null);
    }

}
