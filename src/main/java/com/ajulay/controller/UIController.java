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
                in = in.replaceAll(", ", "&");
                AbstractCommand command = null;
                if (TaskConstant.EXIT.equals(in)) {
                    break;
                }
                if (in.startsWith(TaskConstant.SERVICE_COMMAND_SIGN)) {
                    if (in.startsWith(TaskConstant.CHANGE_STATUS_COMMAND)) {
                        taskData = in.substring(in.indexOf(" ") + 1).split("&");
                        String taskId = taskData[0];
                        String status = taskData[1];
                        taskService.changeStatus(taskId, status);
                        taskService.showTasks();
                        continue;
                    }
                    if (in.startsWith(TaskConstant.DELETE_TASK)) {
                        taskData = new String[]{in.substring(in.indexOf(" ") + 1)};
                        continue;
                    }
                    if (TaskConstant.SHOW_PROJECTS_COMMAND.equals(in)) {
                        command = commands.get(TaskConstant.SHOW_PROJECTS_COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }
                    if (TaskConstant.SHOW_ASSIGNERS_COMMAND.equals(in)) {
                        command = commands.get(TaskConstant.SHOW_ASSIGNERS_COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }
                    if (TaskConstant.SHOW_TASKS_COMMAND.equals(in)) {
                        command = commands.get(TaskConstant.SHOW_TASKS_COMMAND);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }

                    if (in.startsWith(TaskConstant.PROJECT_TASKS)) {
                        taskData = new String[]{in.substring(in.indexOf(" ") + 1)};
                        command = commands.get(TaskConstant.PROJECT_TASKS);
                        System.out.println(command.getDescription());
                        command.execute();
                        continue;
                    }
                    if (in.startsWith(TaskConstant.ADD_NEW_TASK_COMMAND)){
                        
                    }
                } else {
                    taskData = in.split("&", TaskConstant.TASK_DATA_LENGTH);
                    command = commands.get(TaskConstant.ADD_NEW_TASK_COMMAND);
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

    private void enterCommands() {
        commands.put(TaskConstant.SHOW_ASSIGNERS_COMMAND, new ShowAllAssignersCommand(this));
        commands.put(TaskConstant.SHOW_PROJECTS_COMMAND, new ShowAllProjectsCommand(this));
        commands.put(TaskConstant.SHOW_TASKS_COMMAND, new ShowAllTasksCommand(this));
        commands.put(TaskConstant.ADD_NEW_TASK_COMMAND, new AddNewTaskCommand(this));
        commands.put(TaskConstant.PROJECT_TASKS, new ShowTasksByProject(this));
        commands.put(TaskConstant.DELETE_TASK, new DeleteTaskCommand(this));
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
