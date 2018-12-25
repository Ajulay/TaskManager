package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

import java.util.Collection;

public class AppHelpCommand extends AbstractCommand {

    public static final String COMMAND = "/help";

    public AppHelpCommand(ControllerUI controller) {
        super(controller);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "List accessable commands";
    }

    @Override
    public void execute() throws Exception {
        Collection<AbstractCommand> commands = getController().getCommands().values();
        commands.remove(this);
        for (AbstractCommand cmd : commands) {
            System.out.println(cmd.inputCommand() + " - " + cmd.getDescription());
        }
    }
}
