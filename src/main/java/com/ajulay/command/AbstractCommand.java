package com.ajulay.command;

import com.ajulay.controller.ControllerUI;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;

/**
 * AbstractCommand is base class for executing commands
 */
public abstract class AbstractCommand {

    private ControllerUI controller;

    public abstract String getCommandKeyWord();

    public abstract String getDescription();

    public abstract void execute() throws NoSuchTaskException, NoSuchAssignerException, NoSuchProjectException;

    public ControllerUI getController() {
        return controller;
    }

    public void setController(ControllerUI controller) {
        this.controller = controller;
    }

}
