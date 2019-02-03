package com.ajulay.command;

import com.ajulay.controller.ControllerUI;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public abstract class AbstractCommand {

    @Inject
    private ControllerUI controller;

    @Inject
    private Event<AbstractCommand> abstractCommandEvent;

    public abstract String getCommandKeyWord();

    public abstract String getDescription();

    public ControllerUI getController() {
        return controller;
    }

    public Event<AbstractCommand> getAbstractCommandEvent() {
        return abstractCommandEvent;
    }

}
