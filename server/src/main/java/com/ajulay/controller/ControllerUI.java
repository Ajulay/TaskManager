package com.ajulay.controller;

import com.ajulay.api.controller.IControllerUI;
import com.ajulay.api.service.*;
import com.ajulay.api.soap.*;
import com.ajulay.command.*;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.hibernate.HibernateUtil;
import com.ajulay.service.SessionService;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.util.*;

/**
 * ControllerUI - class-controller for interacting
 */
@ApplicationScoped
public class ControllerUI implements IControllerUI {

    private static final Class[] CLASSES = {
            UserFindAllCommand.class, ProjectFindAllCommand.class, TaskCreateCommand.class,
            TaskFindAllByProjectCommand.class, TaskDeleteCommand.class, TaskFindAllByUserCommand.class,
            ProjectCreateCommand.class, UserFindAllByTaskCommand.class, DataBinaryClearCommand.class,
            AppExitCommand.class, TaskChangeStatusCommand.class, AppHelpCommand.class,
            TaskFindAllCommand.class, DataSaveCommand.class, DataLoadCommand.class,
            DataSaveJsonCommand.class, DataLoadJsonCommand.class, DataSaveXmlCommand.class,
            DataLoadXmlCommand.class, UserChangePasswordCommand.class, LogOutCommand.class,
            SessionAllShowCommand.class, SessionFindByIdCommand.class, UserDeleteById.class
    };

    @Inject
    private IProjectSoapService projectSoapService;

    @Inject
    private ITaskSoapService taskSoapService;

    @Inject
    private IUserSoapService userSoapService;

    @Inject
    private IAssigneeSoapService assigneeSoapService;

    @Inject
    private ISessionSoapService sessionSoapService;

    @Inject
    private IUserService userService;

    @Inject
    private IProjectService projectService;

    @Inject
    private ITaskService taskService;

    @Inject
    private IAssigneeService assigneeService;

    @Inject
    private SessionService sessionService;

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);


    public void register(final Class... clazzes) throws Exception {
        for (final Class clazz : clazzes) {
            register(clazz);
        }
    }

    public void register(@NotNull final Class clazz) throws IllegalAccessException, InstantiationException {
        if (!AbstractCommand.class.isAssignableFrom(clazz)) return;
        final AbstractCommand command = (AbstractCommand) clazz.newInstance();
        command.setController(this);
        commands.put(command.getCommandKeyWord(), command);
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void run() throws Exception {
        startHibernate();
        runServices();
        loadData();
        controlSession();

        System.out.println("TASK MANAGER");
        while (true) {
            if (getSessionService().getCurrentSession() == null) {
                commands.clear();
                register(LoginCommand.class, RegistrationCommand.class,
                        AppExitCommand.class,
                        AppHelpCommand.class);
            } else {
                final User currentUser = getUserService().getCurrentUser();
                System.out.printf("User: %s, role: %s\n", currentUser.getSurname(), currentUser.getRole());
            }
            final String in = nextLine();
            if (in == null) continue;
            final AbstractCommand command = commands.get(in);
            if (command != null) {
                System.out.println(command.getDescription());
                try {
                    command.execute();
                    System.out.println("[Ok]");
                } catch (Exception e) {
                    System.out.println("Error!!!" + e.getMessage());
                    e.printStackTrace();
                }
                continue;
            }
            System.out.println("You entered incorrect data... Try again.");
        }
    }

    private void runServices() {
        Endpoint.publish("http://localhost:8080/login", sessionSoapService);
        Endpoint.publish("http://localhost:8080/project", projectSoapService);
        Endpoint.publish("http://localhost:8080/task", taskSoapService);
        Endpoint.publish("http://localhost:8080/user", userSoapService);
        Endpoint.publish("http://localhost:8080/assignee", assigneeSoapService);
    }

    private void loadData() {
        System.out.println("Load data");
        final User admin = getUserService().findByLogin("admin");
        if (admin == null) {
            final User newAdmin = getUserService().createByLogin("admin");
            newAdmin.setSurname("Admin");
            newAdmin.setRole(Role.ADMIN);
            newAdmin.setLogin(ServiceConstant.START_LOGIN);
            newAdmin.setPasswordHash(ServiceConstant.START_PASSWORD_HASH);
            getUserService().update(newAdmin); //TODO problem?
        }

        final User user = getUserService().findByLogin("user");
        if (user == null) {
            final User newAdmin = getUserService().createByLogin("user");
            newAdmin.setSurname("User");
            newAdmin.setRole(Role.WORKER);
            newAdmin.setLogin("user");
            newAdmin.setPasswordHash("user".hashCode() + "");
            getUserService().update(newAdmin);
        }
    }

    private void controlSession() {
        final Thread controlThread = new Thread() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(ServiceConstant.CONTROL_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final List<Session> sessionAllForRemove = new ArrayList<>();
                    for (final Session session : sessionService.findAll()) {
                        final long ldate = new Date().getTime() - session.getCreatedDate().getTime();
                        if (ldate > ServiceConstant.CONTROL_TIME) {
                            sessionAllForRemove.add(session);
                        }
                    }
                    sessionService.removeAll(sessionAllForRemove);
                }
            }
        };
        controlThread.setDaemon(true);
        controlThread.start();
    }


    private void startHibernate() throws IOException {
        HibernateUtil.factory();
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

    public void registerCommandAll() throws Exception {
        register(CLASSES);
    }

    public ISessionService getSessionService() {
        return sessionService;
    }

}
