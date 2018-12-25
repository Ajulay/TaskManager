package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

import java.util.Collection;

public class AppHelpCommand extends AbstractCommand {

    @Override
    public String inputCommand() {
        return "/help";
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
