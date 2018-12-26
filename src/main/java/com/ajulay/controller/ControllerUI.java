package com.ajulay.controller;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IAssignerService;
import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.command.AbstractCommand;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.service.AssigneeService;
import com.ajulay.service.AssignerService;
import com.ajulay.service.ProjectService;
import com.ajulay.service.TaskService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ControllerUI - class-controller for interacting
 */
public class ControllerUI {

    private final IAssignerService assignerService = new AssignerService();

    private final IProjectService projectService = new ProjectService();

    private final ITaskService taskService = new TaskService();

    private final IAssigneeService assigneeService = new AssigneeService();

    private final Map<String, AbstractCommand> commands = new HashMap<>();

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

    public void run() throws Exception {
        testData();
        final Scanner scanner = new Scanner(System.in);
        System.out.println("TASK MANAGER\n" +
                "for help type: /help");
        while (true) {

                String in = scanner.nextLine();
                if (in == null) continue;
            final AbstractCommand command = commands.get(in);
            if (command != null) {
                    System.out.println(command.getDescription());
                    command.execute();
                }
            System.out.println("You entered incorrect data.");
        }
    }

    private void testData() {
        assignerService.getAssigners().add(new Assigner("Alexeev"));
        assignerService.getAssigners().add(new Assigner("Andreev"));
        assignerService.getAssigners().add(new Assigner("Sergeev"));

        Project project1 = new Project("Project1");
        Project project2 = new Project("Project2");
        Project project3 = new Project("Project3");

        projectService.getProjects().add(project1);
        projectService.getProjects().add(project2);
        projectService.getProjects().add(project3);

        taskService.findTaskAll().add(getTestTask(project1.getId(), "2018-12-20", ServiceConstant.HIGH_PRIORITY, "Write application1..."));
        taskService.findTaskAll().add(getTestTask(project2.getId(), "2018-12-20", ServiceConstant.MIDDLE_PRIORITY, "Write application2..."));
        taskService.findTaskAll().add(getTestTask(project3.getId(), "2018-12-20", ServiceConstant.LOW_PRIORITY, "Write application3..."));
        taskService.findTaskAll().add(getTestTask(project1.getId(), "2018-12-20", ServiceConstant.MIDDLE_PRIORITY, "Write application4..."));
        taskService.findTaskAll().add(getTestTask(project2.getId(), "2018-12-20", ServiceConstant.LOW_PRIORITY, "Write application5..."));
    }

    private Task getTestTask(final String projectId, final String sTerm, int priority, final String content) {
        final Task task = new Task();
        task.setProjectId(projectId);
        task.setPriority(priority);
        final String[] datePartArray = sTerm.split("-");
        final String year = datePartArray[0];
        final String month = datePartArray[1];
        final String day = datePartArray[2];
        final Instant term = LocalDate.of(
                Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
                .atStartOfDay().toInstant(ZoneOffset.UTC);
        task.setTerm(term);
        task.setContent(content);
        return task;
    }

    public IAssignerService getAssignerService() {
        return assignerService;
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
}
