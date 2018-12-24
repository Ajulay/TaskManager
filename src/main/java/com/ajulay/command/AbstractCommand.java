package com.ajulay.command;

import com.ajulay.controller.UIController;

import java.util.Map;

public abstract class AbstractCommand {

    private final UIController controller;

    public AbstractCommand(UIController controller) {
        this.controller = controller;
    }
    public abstract String inputCommand();

    public abstract String getDescription();

    public abstract void execute() throws Exception;

    public UIController getController() {
        return controller;
    }
}
