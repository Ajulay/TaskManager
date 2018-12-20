package com.ajulay;

import com.ajulay.executor.Executor;
import com.ajulay.project.Project;
import com.ajulay.service.ExecutorsService;
import com.ajulay.service.ProjectService;
import com.ajulay.service.TaskService;
import com.ajulay.task.OveralTask;
import com.ajulay.task.TaskConstant;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        testData();
        Scanner scanner = new Scanner(System.in);
        System.out.println("TASK MANAGER\n" +
                "to see projects write: pts\n" +
                "to see executors write: ex\n" +
                "to see tasks write: tsk\n" +
                "to add overal task write: project_name, term, priority(1-3), content\n" +
                "to add private task write: projectName, term, priority(1-3), content, executor_surname\n" +
                "to add group task write: project_name, term, priority(1-3), content, executor_surname1, executor_surname2...\n" +
                "to change status task write: /st id(task), NEW (or ONWORKING, FINISHED, FAILED)");
        String[] taskData = null;
        while (true) {
            try {
                String str = scanner.nextLine();
                str = str.replaceAll(", ", "&");
                if (TaskConstant.EXIT.equals(str)) {
                    break;
                }
                if (TaskConstant.SHOW_PROJECTS_COMMAND.equals(str)) {
                    ProjectService.showProjects();
                }
                if (TaskConstant.SHOW_EXECUTORS_COMMAND.equals(str)) {
                    ExecutorsService.showExecutors();
                }
                if (TaskConstant.SHOW_TASKS_COMMAND.equals(str)) {
                    TaskService.showTasks();
                }
                if (TaskConstant.CHANGE_STATUS_COMMAND.startsWith(str)) {
                    taskData = str.substring(str.indexOf(" ") + 1).split("&");
                    final int taskId = Integer.parseInt(taskData[0]);
                    final String status = taskData[1];
                    TaskService.changeStatus(taskId, status);
                    TaskService.showTasks();
                    continue;
                }
                if (str.length() > TaskConstant.SERVICE_WORD_LENGTH) {
                    taskData = str.split("&", TaskConstant.TASK_DATA_LENGTH + 1);
                    final String projectName = taskData[0];
                    final String date = taskData[1];
                    final int priority = Integer.parseInt(taskData[2]);
                    final String content = taskData[3];
                    if (taskData.length == TaskConstant.TASK_DATA_LENGTH) {
                        TaskService.createOveralTask(projectName, date, priority, content);
                    }
                    if (taskData.length > TaskConstant.TASK_DATA_LENGTH) {
                        final String executorsSurnames = taskData[4];

                        if (taskData[4].split("&").length == 1) {
                            TaskService.createPrivateTask(projectName, date, priority, content, executorsSurnames);
                            TaskService.showTasks();
                            continue;
                        }
                        TaskService.createGroupTask(projectName, date, priority, content, executorsSurnames);
                    }
                    TaskService.showTasks();
                    continue;
                }
                System.out.println("You entered wrong data...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    private static void testData() {
        ExecutorsService.getExecutors().add(new Executor("Alexeev"));
        ExecutorsService.getExecutors().add(new Executor("Andreev"));
        ExecutorsService.getExecutors().add(new Executor("Sergeev"));

        ProjectService.getProjects().add(new Project("Project1"));
        ProjectService.getProjects().add(new Project("Project2"));
        ProjectService.getProjects().add(new Project("Project3"));

        TaskService.getTasks().add(new OveralTask("Project1", "2018-12-20", TaskConstant.HIGH_PRIORITY, "Write application1..."));
        TaskService.getTasks().add(new OveralTask("Project2", "2018-12-20", TaskConstant.MIDDLE_PRIORITY, "Write application2..."));
        TaskService.getTasks().add(new OveralTask("Project3", "2018-12-20", TaskConstant.LOW_PRIORITY, "Write application3..."));
        TaskService.getTasks().add(new OveralTask("Project1", "2018-12-20", TaskConstant.MIDDLE_PRIORITY, "Write application4..."));
        TaskService.getTasks().add(new OveralTask("Project2", "2018-12-20", TaskConstant.LOW_PRIORITY, "Write application5..."));
    }
    //Project1, 2018-12-20, 2, create new Application, Alexeev //for convenient presentation
}
