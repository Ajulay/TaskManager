package com.ajulay.command;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collection;

public class AppHelpCommand extends AbstractCommand {

    @Inject
    private Event<AbstractCommand> abstractCommand;

    @Override
    public String getCommandKeyWord() {
        return "/help";
    }

    @Override
    public String getDescription() {
        return "List accessable commands";
    }

    public void execute(@Observes AppHelpCommand appHelpCommand) {
        Collection<AbstractCommand> commands = getController().getCommands().values();
        for (AbstractCommand cmd : commands) {
            System.out.println(cmd.getCommandKeyWord() + " - " + cmd.getDescription());
        }
        System.out.println("[Ok]");
        abstractCommand.fire(getController().getBeginCommand());

    }

}
