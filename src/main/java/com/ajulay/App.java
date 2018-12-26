package com.ajulay;

import com.ajulay.command.*;
import com.ajulay.controller.ControllerUI;

public class App {

    private static final Class[] classes = {
            AppExitCommand.class, AppHelpCommand.class, AssignerFindAllCommand.class,
            ProjectFindAllCommand.class, TaskChangeStatusCommand.class, TaskCreateCommand.class,
            TaskDeleteCommand.class, TaskFindAllByProjectCommand.class, TaskFindAllCommand.class,
            AssignerFindAllByTask.class, TaskFindAllByAssigner.class
    };

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        final ControllerUI controllerUI = new ControllerUI();
        controllerUI.register(classes);
        try {
            controllerUI.run();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO write custom exception
        }
    }
}
