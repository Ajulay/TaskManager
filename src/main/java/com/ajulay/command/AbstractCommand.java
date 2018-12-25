package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

public abstract class AbstractCommand {

    private final ControllerUI controller;

    public AbstractCommand(ControllerUI controller) {
        this.controller = controller;
    }
    public abstract String inputCommand();

    public abstract String getDescription();

    public abstract void execute() throws Exception;

    public ControllerUI getController() {
        return controller;
    }
}
