package com.ajulay.controller;

import com.ajulay.api.controller.IControllerUI;
import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.api.service.IUserService;
import com.ajulay.command.*;
import com.ajulay.entity.*;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;
import com.ajulay.service.AssigneeService;
import com.ajulay.service.ProjectService;
import com.ajulay.service.TaskService;
import com.ajulay.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ControllerUI - class-controller for interacting
 */
public class ControllerUI implements IControllerUI {

    private final IUserService userService = new UserService();

    private final IProjectService projectService = new ProjectService();

    private final ITaskService taskService = new TaskService();

    private final IAssigneeService assigneeService = new AssigneeService();

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private User currentUser;

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

    public String nextLine() {
        return scanner.nextLine();
    }

    public void run() throws Exception {
        final AbstractCommand loadCommand = commands.get(new DataLoadCommand().getCommandKeyWord());
        System.out.println(loadCommand.getDescription());
        loadCommand.execute();
        Thread.sleep(1000);
        System.out.println("TASK MANAGER");
        Thread.sleep(1000);
        while (true) {
            System.out.println("type /login (If You have no login type: /reg)");
            final String login = nextLine();
            try {
                if (login.equals(RegistrationCommand.COMMAND) || login.equals(LoginCommand.COMMAND)) {
                    final AbstractCommand command = commands.get(login);
                    command.execute();
                    if (login.equals(RegistrationCommand.COMMAND)) {
                        AbstractCommand saveCommand = commands.get(new DataSaveCommand().getCommandKeyWord());
                        saveCommand.execute();
                    }
                    System.out.println("[Ok]");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println("You entered incorrect data... Try again.");
        }
        commands.remove(RegistrationCommand.COMMAND);
        commands.remove(LoginCommand.COMMAND);
        if (Role.WORKER.equals(currentUser.getRole())) {
            commands.remove(new UserFindAllByTaskCommand().getCommandKeyWord());
            commands.remove(new UserFindAllCommand().getCommandKeyWord());
            commands.remove(new DataBinaryClearCommand().getCommandKeyWord());
            commands.remove(new ProjectCreateCommand().getCommandKeyWord());
            commands.remove(new ProjectFindAllCommand().getCommandKeyWord());
            commands.remove(new TaskCreateCommand().getCommandKeyWord());
            commands.remove(new TaskDeleteCommand().getCommandKeyWord());
            commands.remove(new TaskFindAllByAssignerCommand().getCommandKeyWord());
            commands.remove(new TaskFindAllByProjectCommand().getCommandKeyWord());
            commands.remove(new DataLoadJsonCommand().getCommandKeyWord());
            commands.remove(new DataSaveXmlCommand().getCommandKeyWord());
        }
        while (true) {
            System.out.println("User: " + currentUser.getSurname() + ", role: " + currentUser.getRole());
            String in = nextLine();
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

    public Domain createDomain() {
        final Domain domain = new Domain();
        final List<Project> projects = getProjectService().getProjects();
        final List<User> assigners = getUserService().getUsers();
        final List<Assignee> assignees = getAssigneeService().findAllAssignee();
        final List<Task> tasks = getTaskService().findTaskAll();
        domain.setProjects(projects);
        domain.setAssigners(assigners);
        domain.setAssignees(assignees);
        domain.setTasks(tasks);
        return domain;
    }

    public Boolean loadDomain(final Domain domain) {
        final List<Project> projects = domain.getProjects();
        final List<Assignee> assignees = domain.getAssignees();
        final List<User> assigners = domain.getAssigners();
        final List<Task> tasks = domain.getTasks();
        getProjectService().merge(projects);
        getAssigneeService().merge(assignees);
        getUserService().merge(assigners);
        getTaskService().merge(tasks);
        return true;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
