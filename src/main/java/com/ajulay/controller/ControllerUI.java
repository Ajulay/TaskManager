package com.ajulay.controller;


import com.ajulay.api.service.IAssignerService;
import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.command.*;
import com.ajulay.constants.TaskConstant;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.service.AssignerService;
import com.ajulay.service.ProjectService;
import com.ajulay.service.TaskService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

public class ControllerUI {

    private final IAssignerService assignerService = new AssignerService();

    private final IProjectService projectService = new ProjectService();

    private final ITaskService taskService = new TaskService();

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    public void register(Class... clazzes) {
        for (Class clazz : clazzes) {
            register(clazz);
        }
    }

    public void register(final Class clazz) {
        if(!AbstractCommand.class.isAssignableFrom(clazz)) return;
        try {
            final AbstractCommand command = (AbstractCommand) clazz.newInstance();
            command.setController(this);
            commands.put(command.inputCommand(), command);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        register(AppExitCommand.class, AppHelpCommand.class, AssignerFindAllCommand.class, ProjectFindAllCommand.class,
                TaskChangeStatusCommand.class, TaskCreateCommand.class, TaskDeleteCommand.class,
                TaskFindAllByProjectCommand.class, TaskFindAllCommand.class);
        testData();
        final Scanner scanner = new Scanner(System.in);
        System.out.println("TASK MANAGER\n" +
                "for help type: /help");
        while (true) {
            try {
                String in = scanner.nextLine();
                if (in == null) continue;
                AbstractCommand command = null;
                if (in.startsWith(TaskConstant.SERVICE_COMMAND_SIGN)) {

                        command = commands.get(in);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;

                }
                System.out.println("You entered incorrect data...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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

        taskService.getTasks().add(getTestTask(project1.getId(), "2018-12-20", TaskConstant.HIGH_PRIORITY, "Write application1..."));
        taskService.getTasks().add(getTestTask(project2.getId(), "2018-12-20", TaskConstant.MIDDLE_PRIORITY, "Write application2..."));
        taskService.getTasks().add(getTestTask(project3.getId(), "2018-12-20", TaskConstant.LOW_PRIORITY, "Write application3..."));
        taskService.getTasks().add(getTestTask(project1.getId(), "2018-12-20", TaskConstant.MIDDLE_PRIORITY, "Write application4..."));
        taskService.getTasks().add(getTestTask(project2.getId(), "2018-12-20", TaskConstant.LOW_PRIORITY, "Write application5..."));
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

}
