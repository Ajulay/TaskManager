package com.ajulay.gui;

import com.ajulay.executor.Executor;
import com.ajulay.project.Project;
import com.ajulay.service.*;
import com.ajulay.task.Task;
import com.ajulay.task.util.TaskConstant;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIController {

    private static ExecutorsService executorsService = new ExecutorsServiceImpl();
    private static ProjectService projectService = new ProjectServiceImpl();
    private static TaskService taskService = new TaskServiceImpl();


    public static void run() {
        testData();
        Scanner scanner = new Scanner(System.in);
        System.out.println("TASK MANAGER\n" +
                "to see projects write: pts\n" +
                "to see executors write: ex\n" +
                "to see tasks write: tsk\n" +
                "to add overal task write: project_name, term, priority(1-3), content\n" +
                "to add private task write: projectName, term, priority(1-3), content, executor_surname\n" +
                "to add group task write: project_name, term, priority(1-3), content, executor_surname1, executor_surname2...\n" +
                "to change status task write: /st id(task), NEW (or ONWORKING, FINISHED, FAILED)" +
                "to delete task write: /dt id(task)");

        String[] taskData = null;
        while (true) {
            try {
                String in = scanner.nextLine();
                if (in == null) continue;
                in = in.replaceAll(", ", "&");
                if (TaskConstant.EXIT.equals(in)) {
                    break;
                }
                if (TaskConstant.SHOW_PROJECTS_COMMAND.equals(in)) {
                    projectService.showProjects();
                    continue;
                }
                if (TaskConstant.SHOW_EXECUTORS_COMMAND.equals(in)) {
                    executorsService.showExecutors();
                    continue;
                }
                if (TaskConstant.SHOW_TASKS_COMMAND.equals(in)) {
                    taskService.showTasks();
                    continue;
                }
                if (in.startsWith(TaskConstant.SERVICE_COMMAND)) {
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
                }
                if (in.length() > TaskConstant.SERVICE_WORD_LENGTH) {
                    taskData = in.split("&", TaskConstant.TASK_DATA_LENGTH);
                    final String projectName = taskData[0];
                    final String sTerm = taskData[1];
                    final int priority = Integer.parseInt(taskData[2]);
                    final String content = taskData[3];
                    final String executorsSurname = taskData[4];

                        Task task = new Task();
                        task.setProjectName(projectName);
                        task.setContent(content);
                        String[] datePartArray = sTerm.split("-");
                        String year = datePartArray[0];
                        String month = datePartArray[1];
                        String day = datePartArray[2];
                        Instant term = LocalDate.of(
                                Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
                                .atStartOfDay().toInstant(ZoneOffset.UTC);
                        task.setTerm(term);
                        task.setPriority(priority);
                        List<Executor> tmpExecutors = new ArrayList<>();
                        try{
                            String[] executorsSurnames = executorsSurname.split("&");

                        for (String surname : executorsSurnames) {
                            Executor executor = executorsService.getBySurname(surname);
                            executor.getTasks().add(task);
                            tmpExecutors.add(executor);
                        }
                        }catch (Exception e){
                            for (Executor executor : tmpExecutors) {
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

    private static void testData() {
        executorsService.getExecutors().add(new Executor("Alexeev"));
        executorsService.getExecutors().add(new Executor("Andreev"));
        executorsService.getExecutors().add(new Executor("Sergeev"));

        projectService.getProjects().add(new Project("Project1"));
        projectService.getProjects().add(new Project("Project2"));
        projectService.getProjects().add(new Project("Project3"));

        taskService.getTasks().add(getTestTask("Project1", "2018-12-20", TaskConstant.HIGH_PRIORITY, "Write application1..."));

        taskService.getTasks().add(getTestTask("Project2", "2018-12-20", TaskConstant.MIDDLE_PRIORITY, "Write application2..."));
        taskService.getTasks().add(getTestTask("Project3", "2018-12-20", TaskConstant.LOW_PRIORITY, "Write application3..."));
        taskService.getTasks().add(getTestTask("Project1", "2018-12-20", TaskConstant.MIDDLE_PRIORITY, "Write application4..."));
        taskService.getTasks().add(getTestTask("Project2", "2018-12-20", TaskConstant.LOW_PRIORITY, "Write application5..."));
    }

    private static Task getTestTask(String projectName, String sTerm, int priority, String content){
        Task task = new Task();
        task.setProjectName(projectName);
        task.setPriority(priority);
        String[] datePartArray = sTerm.split("-");
        String year = datePartArray[0];
        String month = datePartArray[1];
        String day = datePartArray[2];
        Instant term = LocalDate.of(
                Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
                .atStartOfDay().toInstant(ZoneOffset.UTC);
        task.setTerm(term);
        task.setContent(content);
    return task;}
    //Project1, 2018-12-20, 2, create new Application, Alexeev //for convenient presentation
}
