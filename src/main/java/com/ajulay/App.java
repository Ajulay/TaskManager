package com.ajulay;

import com.ajulay.command.*;
import com.ajulay.controller.ControllerUI;

public class App {

    public static void main(String[] args) {
        ControllerUI controllerUI = new ControllerUI();

        try {
            controllerUI.register(AppExitCommand.class, AppHelpCommand.class, AssignerFindAllCommand.class, ProjectFindAllCommand.class,
                    TaskChangeStatusCommand.class, TaskCreateCommand.class, TaskDeleteCommand.class,
                    TaskFindAllByProjectCommand.class, TaskFindAllCommand.class, AssignerFindAllByTask.class,
                    TaskFindAllByAssigner.class);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        controllerUI.run();
    }
}
