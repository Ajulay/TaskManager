package com.ajulay;

import com.ajulay.command.*;
import com.ajulay.controller.ControllerUI;

public class App {

    private static final Class[] classes = {
            AppExitCommand.class, AppHelpCommand.class, UserFindAllCommand.class,
            ProjectFindAllCommand.class, TaskChangeStatusCommand.class, TaskCreateCommand.class,
            TaskDeleteCommand.class, TaskFindAllByProjectCommand.class, TaskFindAllCommand.class,
            UserFindAllByTask.class, TaskFindAllByAssigner.class, DataSaveCommand.class,
            DataLoadCommand.class, DataBinaryClearCommand.class, RegistrationCommand.class,
            LoginCommand.class, ProjectCreateCommand.class
    };

    public static void main(String[] args) throws Exception {
        final ControllerUI controllerUI = new ControllerUI();
        controllerUI.register(classes);
            controllerUI.run();
    }

}
