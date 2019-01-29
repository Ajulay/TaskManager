package com.ajulay.controller;

import com.ajulay.api.controller.IControllerUI;
import com.ajulay.api.service.*;
import com.ajulay.api.soap.*;
import com.ajulay.command.*;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.dao.util.DataBaseConnection;
import com.ajulay.endpoint.*;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.hibernate.HibernateUtil;
import com.ajulay.mybatis.mapper.MyBatisUserDao;
import com.ajulay.service.OveralService;
import com.ajulay.service.SessionService;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.io.FileInputStream;
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
    private IUserService userService; //= new UserService();

    @Inject
    private IProjectService projectService; //= new ProjectService();

    @Inject
    private ITaskService taskService; //= new TaskService();

    @Inject
    private IAssigneeService assigneeService;// = new AssigneeService();

    @Inject
    private SessionService sessionService; // = new SessionService();

    @Inject
    private OveralService overalService; //= new OveralService(assigneeService, userService, projectService,
    //taskService, sessionService);

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private final DataBaseConnection conn = new DataBaseConnection();

    private SqlSessionFactory sqlSessionFactory;

    private SessionFactory sessionFactory;

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
        setConnection();
        runServices();
        startMyBatis();
        loadData();
        controlSession();
        startHibernate();
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
        final ISessionSoapService sessionSoapService = new SessionSoapEndPoint(overalService);
        Endpoint.publish("http://localhost:8080/login", sessionSoapService);
        final IProjectSoapService projectSoapService = new ProjectSoapEndPoint(overalService);
        Endpoint.publish("http://localhost:8080/project", projectSoapService);
        final ITaskSoapService taskSoapService = new TaskSoapEndPoint(overalService);
        Endpoint.publish("http://localhost:8080/task", taskSoapService);
        final IUserSoapService userSoapService = new UserSoapEndPoint(overalService);
        Endpoint.publish("http://localhost:8080/user", userSoapService);
        final IAssigneeSoapService assigneeSoapService = new AssigneeSoapEndPoint(overalService);
        Endpoint.publish("http://localhost:8080/assignee", assigneeSoapService);
    }

    private void setConnection() throws Exception {
        conn.connect();
        getProjectService().getProjectDAO().setConn(conn.getConn());
        getSessionService().getSessionDao().setConn(conn.getConn());
        getAssigneeService().getAssigneeDAO().setConn(conn.getConn());
        getTaskService().getDao().setSessionFactory(sessionFactory);
    }
    private void loadData() throws Exception {
        System.out.println("Load data");
        final User admin = getUserService().findByLogin("admin");
        if (admin == null) {
            final User newAdmin = getUserService().createUser("admin");
            newAdmin.setSurname("Admin");
            newAdmin.setRole(Role.ADMIN);
            newAdmin.setLogin(ServiceConstant.START_LOGIN);
            newAdmin.setPassword(ServiceConstant.START_PASSWORD_HASH);
            getUserService().mergeUser(newAdmin);
        }
        Thread.sleep(ServiceConstant.LOAD_TIME);
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
                    for (final Session session : sessionService.findSessionAll()) {
                        final long ldate = new Date().getTime() - session.getCreatedDate().getTime();
                        if (ldate > ServiceConstant.CONTROL_TIME) {
                            sessionAllForRemove.add(session);
                        }
                    }
                    sessionService.deleteSessionAll(sessionAllForRemove);
                }
            }
        };
        controlThread.setDaemon(true);
        controlThread.start();
    }

    private void startMyBatis() throws IOException {
        final FileInputStream fis = new FileInputStream(ServiceConstant.HIBERNATE_PROPERTY_ADDRESS);
        final Properties property = new Properties();
        property.load(fis);
        String username = property.getProperty(ServiceConstant.HIBERNATE_USER);
        String password = property.getProperty(ServiceConstant.HIBERNATE_PASSWORD);
        username = ServiceConstant.EMPTY.equals(username) ? null : username;
        password = ServiceConstant.EMPTY.equals(password) ? null : password;
        final DataSource dataSource = new PooledDataSource(
                property.getProperty(ServiceConstant.HIBERNATE_DRIVER),
                property.getProperty(ServiceConstant.HIBERNATE_CONNECT),
                username, password);
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();

        final Environment environment = new Environment("development",
                transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(MyBatisUserDao.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        getUserService().getUserDao().setSqlSessionFactory(sqlSessionFactory);

    }

    private void startHibernate() throws IOException {
        sessionFactory = HibernateUtil.factory();
        getTaskService().getDao().setSessionFactory(sessionFactory);
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

    public IOveralService getOveralService() {
        return overalService;
    }

    public void registerCommandAll() throws Exception {
        register(CLASSES);
    }

    public ISessionService getSessionService() {
        return sessionService;
    }

}
