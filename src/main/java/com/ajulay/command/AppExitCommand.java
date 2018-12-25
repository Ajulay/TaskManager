package com.ajulay.command;

public class AppExitCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/exit";
    }

    @Override
    public String getDescription() {
        return "BYE!";
    }

    @Override
    public void execute() throws Exception {
        System.exit(0);
    }
}
