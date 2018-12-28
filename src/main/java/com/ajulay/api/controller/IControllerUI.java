package com.ajulay.api.controller;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IAssignerService;
import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.command.AbstractCommand;

import java.util.Map;
import java.util.Scanner;

public interface IControllerUI {

    void register(Class... clazzes) throws InstantiationException, IllegalAccessException;

    void register(final Class clazz) throws IllegalAccessException, InstantiationException;

    String nextLine();

    void run() throws Exception;

    IAssignerService getAssignerService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    Map<String, AbstractCommand> getCommands();

    IAssigneeService getAssigneeService();

    Scanner getScanner();


}
