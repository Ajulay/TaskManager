package com.ajulay.api;

import com.ajulay.command.AbstractCommand;
import com.ajulay.command.BeginCommand;
import com.ajulay.endpoint.*;
import org.jetbrains.annotations.Nullable;

import java.lang.Exception;
import java.util.Map;

public interface IController {
    void register(Class... clazzes) throws InstantiationException, IllegalAccessException, Exception;

    void register(final Class clazz) throws IllegalAccessException, InstantiationException;

    String nextLine();

    void run() throws Exception;

    Map<String, AbstractCommand> getCommands();

    ProjectSoapEndPointService getProjectService();

    Session getCurrentSession();

    SessionSoapEndPointService getSessionService();

    TaskSoapEndPointService getTaskService();

    AssigneeSoapEndPointService getAssigneeService();

    void setCurrentSession(@Nullable Session currentSession);

    void registerCommandAll() throws IllegalAccessException, InstantiationException;

    UserSoapEndPointService getUserService();

    BeginCommand getBeginCommand();

}
