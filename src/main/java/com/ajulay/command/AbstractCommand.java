package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

/**
 * AbstractCommand is base class for executing commands
 */
public abstract class AbstractCommand {

    private ControllerUI controller;

    public abstract String inputCommand();

    public abstract String getDescription();

    public abstract void execute() throws Exception;

    public ControllerUI getController() {
        return controller;
    }

    public void setController(ControllerUI controller) {
        this.controller = controller;
    }

}
