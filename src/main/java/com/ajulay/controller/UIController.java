package com.ajulay.controller;


import com.ajulay.api.service.IAssignerService;
import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ITaskService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIController {

    private final IAssignerService assignerService = new AssignerService();
    private final IProjectService projectService = new ProjectService();
    private final ITaskService taskService = new TaskService();

    public void run() {
        testData();
        final Scanner scanner = new Scanner(System.in);
        System.out.println("TASK MANAGER\n" +
                "to see projects write: /pts\n" +
                "to see executors write: /ex\n" +
                "to see tasks write: /tks\n" +
                "to add group task write: project_id, term, priority(1-3), content (, executor_surname1, executor_surname2...)\n" +
                "to change status task write: /st id(task), NEW (or ONWORKING, FINISHED, FAILED)" +
                "to delete task write: /dt id(task)" +
                "to see all tasks in project write: /ptks project_id");

        String[] taskData = null;
        while (true) {
            try {
                String in = scanner.nextLine();
                if (in == null) continue;
                in = in.replaceAll(", ", "&");
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
                        in = in.substring(in.indexOf(" ") + 1);
                        taskService.deleteTask(in);
                        taskService.showTasks();
                        continue;
                    }
                    if (TaskConstant.SHOW_PROJECTS_COMMAND.equals(in)) {
                        projectService.showProjects();
                        continue;
                    }
                    if (TaskConstant.SHOW_EXECUTORS_COMMAND.equals(in)) {
                        assignerService.showAssigners();
                        continue;
                    }
                    if (TaskConstant.SHOW_TASKS_COMMAND.equals(in)) {
                        taskService.showTasks();
                        continue;
                    }

                    if (in.startsWith(TaskConstant.PROJECT_TASKS)) {
                        in = in.substring(in.indexOf(" ") + 1);
                        taskService.showTasksByProject(in);
                        continue;
                    }

                } else {
                    taskData = in.split("&", TaskConstant.TASK_DATA_LENGTH);
                    final String projectId = taskData[0];
                    final String sTerm = taskData[1];
                    final int priority = Integer.parseInt(taskData[2]);
                    final String content = taskData[3];
                    final String executorsSurname = taskData[4];

                    final Task task = new Task();
                    task.setProjectId(projectId);
                    task.setContent(content);
                    final String[] datePartArray = sTerm.split("-");
                    final String year = datePartArray[0];
                    final String month = datePartArray[1];
                    final String day = datePartArray[2];
                    final Instant term = LocalDate.of(
                            Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
                            .atStartOfDay().toInstant(ZoneOffset.UTC);
                    task.setTerm(term);
                    task.setPriority(priority);
                    final List<Assigner> tmpExecutors = new ArrayList<>();
                    try {
                        final String[] executorsSurnames = executorsSurname.split("&");

                        for (String surname : executorsSurnames) {
                            Assigner executor = assignerService.getBySurname(surname);
                            executor.getTasks().add(task);
                            tmpExecutors.add(executor);
                        }
                    } catch (Exception e) {
                        for (Assigner executor : tmpExecutors) {
                            executor.getTasks().remove(task);
                        }
                        throw new Exception("no such executor");
                    }
                    taskService.saveTask(task);
                    taskService.showTasks();
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

}
