package com.ajulay.command;

public class BeginCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "start";
    }

}
