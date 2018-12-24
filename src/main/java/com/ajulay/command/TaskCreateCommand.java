package com.ajulay.command;

import com.ajulay.command.AbstractCommand;
import com.ajulay.constants.TaskConstant;
import com.ajulay.controller.UIController;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Task;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    public static final String COMMAND = "/tsk";

    public TaskCreateCommand(UIController uiController) {
        super(uiController);
    }

    @Override
    public String inputCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "Add new task";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter: project_id (required)");
        final Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        if (in == null || getController().getProjectService().getById(in) == null) return;
        final Task task = new Task();
        task.setProjectId(in);
        System.out.println("Enter term in format: yyyy-MM-dd");
        in = scanner.nextLine();
        Instant term = null;
        if (in == null) {
            term = Instant.now();
        }
        else {
            final String[] datePartArray = in.split("-");
            final String year = datePartArray[0];
            final String month = datePartArray[1];
            final String day = datePartArray[2];
            term = LocalDate.of(
                    Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
                    .atStartOfDay().toInstant(ZoneOffset.UTC);
        }
            task.setTerm(term);

        System.out.println("Enter priority (1 - 3)");
        in = scanner.nextLine();
        if (in == null){
            task.setPriority(TaskConstant.LOW_PRIORITY);
        }
        else{
            task.setPriority(Integer.parseInt(in));
        }
        System.out.println("Enter content");
        
//        in = in.replaceAll(", ", "&");
//        final String[] taskData = in.split("&");
//        final String projectId = taskData[0];
//        final String sTerm = taskData[1];
//        final int priority = Integer.parseInt(taskData[2]);
//        final String content = taskData[3];
//        final String assignerSurname = taskData[4];
//        final Task task = new Task();
//        task.setProjectId(projectId);
//        task.setContent(content);
//        final String[] datePartArray = sTerm.split("-");
//        final String year = datePartArray[0];
//        final String month = datePartArray[1];
//        final String day = datePartArray[2];
//        final Instant term = LocalDate.of(
//                Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
//                .atStartOfDay().toInstant(ZoneOffset.UTC);
//        task.setTerm(term);
//        task.setPriority(priority);
//        final List<Assigner> tmpAssigners = new ArrayList<>();
//        try {
//            final String[] assignerSurnames = assignerSurname.split("&");
//            for (String surname : assignerSurnames) {
//                final Assigner assigner = getController().getAssignerService().getBySurname(surname);
//                assigner.getTasks().add(task);
//                tmpAssigners.add(assigner);
//            }
//        } catch (Exception e) {
//            for (Assigner executor : tmpAssigners) {
//                executor.getTasks().remove(task);
//            }
//            throw new Exception("no such executor");
//        }
            getController().getTaskService().saveTask(task);
            getController().getTaskService().getTasks();
        }

}
