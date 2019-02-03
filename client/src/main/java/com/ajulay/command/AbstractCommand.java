package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

import javax.inject.Inject;

public abstract class AbstractCommand {

    @Inject
    private ControllerUI controller;

    public abstract String getCommandKeyWord();

    public abstract String getDescription();

    public ControllerUI getController() {
        return controller;
    }

    public void setController(ControllerUI controller) {
        this.controller = controller;
    }

}
