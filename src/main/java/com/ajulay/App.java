package com.ajulay;

import com.ajulay.command.*;
import com.ajulay.controller.ControllerUI;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;

public class App {

    private static final Class[] classes = {
            AppExitCommand.class, AppHelpCommand.class, AssignerFindAllCommand.class,
            ProjectFindAllCommand.class, TaskChangeStatusCommand.class, TaskCreateCommand.class,
            TaskDeleteCommand.class, TaskFindAllByProjectCommand.class, TaskFindAllCommand.class,
            AssignerFindAllByTask.class, TaskFindAllByAssigner.class
    };

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchTaskException, NoSuchProjectException, NoSuchAssignerException {
        final ControllerUI controllerUI = new ControllerUI();
        controllerUI.register(classes);

        try {
            controllerUI.run();
        } catch (NoSuchTaskException | NoSuchProjectException | NoSuchAssignerException e) {
            throw e;
        } finally {
            controllerUI.getScanner().close();
        }
    }
}
