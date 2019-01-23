package com.ajulay.command;

public class DataBDLoadCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/dbload";
    }

    @Override
    public String getDescription() {
        return "load data from database";
    }

    @Override
    public void execute() {

    }

}
