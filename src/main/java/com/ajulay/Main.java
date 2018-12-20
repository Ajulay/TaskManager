package com.ajulay;

import com.ajulay.executors.Executor;
import com.ajulay.projects.Project;
import com.ajulay.services.ExecutorService;
import com.ajulay.services.ProjectService;
import com.ajulay.services.TaskService;
import com.ajulay.tasks.AbstractTask;
import com.ajulay.tasks.GroupTask;
import com.ajulay.tasks.OveralTask;
import com.ajulay.tasks.PrivateTask;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        test();
        Scanner scanner = new Scanner(System.in);
        System.out.println("TASK MANAGER\n" +
                "to see projects write: pts\n" +
                "to see executors write: ex\n" +
                "to see tasks write: tsk\n" +
                "to add overal task write: project_name, term, priority(1-3), content\n" +
                "to add private task write: projectName, term, priority, content, executor_surname\n" +
                "to add group task write: project_name, term, priority, content, executor_surname1, executor_surname2...\n" +
                "to change status task write: tsk id(task), NEW (or ONWORKING, FINISHED, FAILED)");

        String[] taskData = null;
        while (true){
            String str = scanner.nextLine();
                if(str.equals("/exit")){
                    break;
                }
                if(str.equals("pts")){
                    ProjectService.showProjects();
                }
                if(str.equals("ex")){
                    ExecutorService.showExecutors();
                }
            if(str.equals("tks")){
                TaskService.showTasks();
            }
            if(str.length() > 5){
                    taskData = str.split(", ", 4);
                    if(taskData.length == 4){
                        TaskService.getTasks().add(new OveralTask(taskData[0], taskData[1], Integer.parseInt(taskData[2]), taskData[3]));

                    }
                    if(taskData.length == 5){
                    TaskService.getTasks().add(
                            new PrivateTask(taskData[0], taskData[1], Integer.parseInt(taskData[2]), taskData[3], taskData[4]));

                    }
                    if(taskData.length > 5){
                    TaskService.getTasks().add(
                            new GroupTask(taskData[0], taskData[1], Integer.parseInt(taskData[2]), taskData[3], taskData[4]));
                    }
                TaskService.showTasks();
            }
            if(str.startsWith("tsk")){
                taskData = str.substring(4).split(", ");
                try {
                    TaskService.changeStatus(Integer.parseInt(taskData[0]), taskData[1]);
                    TaskService.showTasks();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    }

    private static void test() {
        ExecutorService.getExecutors().add(new Executor("Alexeev"));
        ExecutorService.getExecutors().add(new Executor("Andreev"));
        ExecutorService.getExecutors().add(new Executor("Sergeev"));
        ProjectService.getProjects().add(new Project("Project1"));
        ProjectService.getProjects().add(new Project("Project2"));
        ProjectService.getProjects().add(new Project("Project3"));

        TaskService.getTasks().add(new OveralTask("Project1", "2018-12-20", 1, "Write application1..."));
        TaskService.getTasks().add(new OveralTask("Project2", "2018-12-20", 5, "Write application2..."));
        TaskService.getTasks().add(new OveralTask("Project3", "2018-12-20", 3, "Write application3..."));
        TaskService.getTasks().add(new OveralTask("Project1", "2018-12-20", 2, "Write application4..."));
        TaskService.getTasks().add(new OveralTask("Project2", "2018-12-20", 4, "Write application5..."));
    }

}
