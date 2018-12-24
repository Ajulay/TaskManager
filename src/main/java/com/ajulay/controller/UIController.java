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

public class UIController {

    private final IAssignerService assignerService = new AssignerService();
    private final IProjectService projectService = new ProjectService();
    private final ITaskService taskService = new TaskService();
    private final Map<String, AbstractCommand> commands = new HashMap<>();
    private String[] taskData = null;

    public void run() {
        enterCommands();
        testData();
        final Scanner scanner = new Scanner(System.in);
        System.out.println("TASK MANAGER\n" +
                "to see projects write: /pts\n" +
                "to see executors write: /ex\n" +
                "to see tasks write: /tks\n" +
                "to add task write: project_id, term, priority(1-3), content (, executor_surname1, executor_surname2...)\n" +
                "to change status task write: /st id(task), NEW (or ONWORKING, FINISHED, FAILED)\n" +
                "to delete task write: /dt id(task)\n" +
                "to see all tasks in project write: /ptks project_id");

        while (true) {
            try {
                String in = scanner.nextLine();
                if (in == null) continue;
                AbstractCommand command = null;
                if (in.startsWith(TaskConstant.SERVICE_COMMAND_SIGN)) {

                    if (AppExitCommand.COMMAND.equals(in)) {
                       command = commands.get(AppExitCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        break;
                    }

                    if (AssignerFindAllCommand.COMMAND.equals(in)) {
                        command = commands.get(AssignerFindAllCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }

                    if (ProjectFindAllCommand.COMMAND.equals(in)){
                        command = commands.get(ProjectFindAllCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }

                    if(TaskChangeStatusCommand.COMMAND.equals(in)){
                        command = commands.get(TaskChangeStatusCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }

                    if(TaskCreateCommand.COMMAND.equals(in)){
                        command = commands.get(TaskCreateCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }

                    if (TaskDeleteCommand.COMMAND.equals(in)) {
                        command = commands.get(TaskDeleteCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }

                    if (TaskFindAllByProjectCommand.COMMAND.equals(in)) {
                        command = commands.get(TaskFindAllByProjectCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }

                    if (TaskFindAllCommand.COMMAND.equals(in)) {
                        command = commands.get(TaskFindAllCommand.COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }
                }
                System.out.println("You entered incorrect data...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void enterCommands() {
        commands.put(AppExitCommand.COMMAND, new AppExitCommand(this));
        commands.put(AssignerFindAllCommand.COMMAND, new AssignerFindAllCommand(this));
        commands.put(ProjectFindAllCommand.COMMAND, new ProjectFindAllCommand(this));
        commands.put(TaskChangeStatusCommand.COMMAND, new TaskChangeStatusCommand(this));
        commands.put(TaskFindAllCommand.COMMAND, new TaskFindAllCommand(this));
        commands.put(TaskCreateCommand.COMMAND, new TaskCreateCommand(this));
        commands.put(TaskFindAllByProjectCommand.COMMAND, new TaskFindAllByProjectCommand(this));
        commands.put(TaskDeleteCommand.COMMAND, new TaskDeleteCommand(this));
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

    public String[] getTaskData() {
        return taskData;
    }
}
