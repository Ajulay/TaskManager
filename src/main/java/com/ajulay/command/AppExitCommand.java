package com.ajulay.command;

import com.ajulay.controller.UIController;

public class AppExitCommand extends AbstractCommand {

    public static final String COMMAND = "/exit";

    public AppExitCommand(UIController controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
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
