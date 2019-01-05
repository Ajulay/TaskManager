package com.ajulay.api.controller;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.api.service.IUserService;
import com.ajulay.command.AbstractCommand;

import java.util.Map;
import java.util.Scanner;

public interface IControllerUI {

    void register(Class... clazzes) throws InstantiationException, IllegalAccessException;

    void register(final Class clazz) throws IllegalAccessException, InstantiationException;

    String nextLine();

    void run() throws Exception;

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    Map<String, AbstractCommand> getCommands();

    IAssigneeService getAssigneeService();

    Scanner getScanner();


}
