package com.ajulay;

import com.ajulay.command.*;
import com.ajulay.controller.ControllerUI;

public class App {

    private static final Class[] classes = {
            AppExitCommand.class, AppHelpCommand.class, AssignerFindAllCommand.class,
            ProjectFindAllCommand.class, TaskChangeStatusCommand.class, TaskCreateCommand.class,
            TaskDeleteCommand.class, TaskFindAllByProjectCommand.class, TaskFindAllCommand.class,
            AssignerFindAllByTask.class, TaskFindAllByAssigner.class, DataSaveCommand.class,
            DataLoadCommand.class, BinaryDataClearCommand.class
    };

    public static void main(String[] args) throws Exception {
        final ControllerUI controllerUI = new ControllerUI();
        controllerUI.register(classes);
            controllerUI.run();
    }

}
