package com.ajulay.controller;

import com.ajulay.api.IController;
import com.ajulay.command.*;
import com.ajulay.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@ApplicationScoped
public class ControllerUI implements IController {
    private static final Class[] CLASSES = {
            UserFindAllCommand.class, ProjectFindAllCommand.class, TaskCreateCommand.class,
            TaskFindAllByProjectCommand.class, TaskDeleteCommand.class, TaskFindAllByUserCommand.class,
            ProjectCreateCommand.class, LogOutCommand.class, UserFindAllByTaskCommand.class,
            AppExitCommand.class, UserChangeSurnameCommand.class, SessionDeleteCommand.class,
            AppHelpCommand.class, UserChangePasswordCommand.class, SessionAllShowCommand.class,
            TaskFindAllCommand.class, TaskChangeStatusCommand.class
    };

    @Inject
    private Event<AbstractCommand> abstractCommandEvent;

    private final UserSoapEndPointService userService = new UserSoapEndPointService();

    private final ProjectSoapEndPointService projectService = new ProjectSoapEndPointService();

    private final TaskSoapEndPointService taskService = new TaskSoapEndPointService();

    private final SessionSoapEndPointService sessionService = new SessionSoapEndPointService();

    private final AssigneeSoapEndPointService assigneeService = new AssigneeSoapEndPointService();

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    private final Map<String, Event> events = new HashMap<>();

    private Session currentSession = null;

    private final Scanner scanner = new Scanner(System.in);

    public void register(Class... clazzes) throws InstantiationException, IllegalAccessException {
        for (Class clazz : clazzes) {
            register(clazz);
        }
    }

    public void register(final Class clazz) throws IllegalAccessException, InstantiationException {
        if (!AbstractCommand.class.isAssignableFrom(clazz)) return;
        final AbstractCommand command = (AbstractCommand) clazz.newInstance();
        commands.put(command.getCommandKeyWord(), command);
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void run() throws Exception {
        System.out.println("TASK MANAGER");
        while (true) {
            if (currentSession == null) {
                commands.clear();
                register(
                        LoginCommand.class,
                        RegistrationCommand.class,
                        AppExitCommand.class, AppHelpCommand.class
                );
            } else {
                final String currentUserName = currentSession.getUserId();
                System.out.printf("User id: %s\n", currentUserName);

            }
            final String in = nextLine();
            if (in == null) continue;
            final AbstractCommand command = commands.get(in);
            if (command != null) {
                System.out.println(command.getDescription());
                try {
                    abstractCommandEvent.fire(command);
                    System.out.println("[Ok]");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            System.out.println("You entered incorrect data... Try again.");
        }
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public void registerCommandAll() throws IllegalAccessException, InstantiationException {
        register(CLASSES);
    }

    public UserSoapEndPointService getUserService() {
        return userService;
    }

    public ProjectSoapEndPointService getProjectService() {
        return projectService;
    }

    public TaskSoapEndPointService getTaskService() {
        return taskService;
    }

    public SessionSoapEndPointService getSessionService() {
        return sessionService;
    }

    public AssigneeSoapEndPointService getAssigneeService() {
        return assigneeService;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

}
