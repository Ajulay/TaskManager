package com.ajulay.command;

public class AppExitCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/exit";
    }

    @Override
    public String getDescription() {
        return "Bye!";
    }

    @Override
    public void execute() {
        System.out.println(getDescription());
        System.exit(0);
    }

}
