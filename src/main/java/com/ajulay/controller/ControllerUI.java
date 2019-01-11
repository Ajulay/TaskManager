package com.ajulay.controller;

import com.ajulay.api.controller.IControllerUI;
import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.api.service.IUserService;
import com.ajulay.command.*;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;
import com.ajulay.service.AssigneeService;
import com.ajulay.service.ProjectService;
import com.ajulay.service.TaskService;
import com.ajulay.service.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ControllerUI - class-controller for interacting
 */
public class ControllerUI implements IControllerUI {

    private static final Class[] additionalCommand = {
            UserFindAllCommand.class, ProjectFindAllCommand.class,
            TaskCreateCommand.class, TaskFindAllByProjectCommand.class,
            TaskDeleteCommand.class, TaskFindAllByAssignerCommand.class,
            ProjectCreateCommand.class, UserFindAllByTaskCommand.class,
            DataBinaryClearCommand.class,
    };

    private static final Class[] baseCommand = {
            AppExitCommand.class, TaskChangeStatusCommand.class, AppHelpCommand.class,
            TaskFindAllCommand.class, DataSaveCommand.class, DataLoadCommand.class,
            DataSaveJsonCommand.class, DataLoadJsonCommand.class, DataSaveXmlCommand.class,
            DataLoadXmlCommand.class, ChangePasswordCommand.class, LogOutCommand.class
    };

    private final IUserService userService = new UserService();

    private final IProjectService projectService = new ProjectService();

    private final ITaskService taskService = new TaskService();

    private final IAssigneeService assigneeService = new AssigneeService();

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    public void register(Class... clazzes) throws InstantiationException, IllegalAccessException {
        for (Class clazz : clazzes) {
            register(clazz);
        }
    }

    public void register(final Class clazz) throws IllegalAccessException, InstantiationException {
        if (!AbstractCommand.class.isAssignableFrom(clazz)) return;
        final AbstractCommand command = (AbstractCommand) clazz.newInstance();
        command.setController(this);
        commands.put(command.getCommandKeyWord(), command);
    }

    public void registerCommandAll() throws IllegalAccessException, InstantiationException {
        register(additionalCommand);
        register(baseCommand);
    }

    public void registerBaseCommandAll() throws IllegalAccessException, InstantiationException {
        register(baseCommand);
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void run() throws Exception {
        loadData();
        System.out.println("TASK MANAGER");
        while (true) {
            if (getUserService().getCurrentUser() == null) {
                commands.clear();
                register(LoginCommand.class, RegistrationCommand.class, AppExitCommand.class, AppHelpCommand.class);
            } else {
                final String currentUserName = getUserService().getCurrentUser().getSurname();
                final String currentUserRole = getUserService().getCurrentUser().getRole().toString();
                System.out.printf("User: %s, role: %s\n", currentUserName, currentUserRole);
            }
            final String in = nextLine();
            if (in == null) continue;
            final AbstractCommand command = commands.get(in);
            if (command != null) {
                System.out.println(command.getDescription());
                try {
                    command.execute();
                    System.out.println("[Ok]");
                } catch (NoSuchTaskException |
                        NoSuchAssignerException |
                        NoSuchProjectException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            System.out.println("You entered incorrect data... Try again.");
        }
    }

    private void loadData() throws Exception {
        System.out.println("Load data");
        final Path path = Paths.get(ServiceConstant.DATA_FILE);
        if (!Files.exists(path)) {
            final User user = getUserService().createUser("admin");
            user.setRole(Role.ADMIN);
            user.setLogin(ServiceConstant.START_LOGIN);
            user.setPassword(ServiceConstant.START_PASSWORD_HASH);
        } else {
            final AbstractCommand tmpCommand = new DataLoadCommand();
            tmpCommand.setController(this);
            tmpCommand.execute();
        }
        Thread.sleep(500);
    }

    public IUserService getUserService() {
        return userService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public IAssigneeService getAssigneeService() {
        return assigneeService;
    }

    public Scanner getScanner() {
        return scanner;
    }

}
