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

public class AddNewTaskCommand extends AbstractCommand {

    public AddNewTaskCommand(UIController uiController) {
        super(uiController);
    }

    @Override
    public String inputCommand() {
        return TaskConstant.ADD_NEW_TASK_COMMAND;
    }

    @Override
    public String getDescription() {
        return "Add new task";
    }

    @Override
    public void execute() throws Exception {
        final Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        if (in == null) return;
        in = in.replaceAll(", ", "&");
        System.out.println("Enter: project_id, term, priority(1-3), content (, executor_surname1, executor_surname2...)");
        final String[] taskData = in.split("&");
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
        final List<Assigner> tmpAssigners = new ArrayList<>();
        try {
            final String[] assignerSurnames = executorsSurname.split("&");
            for (String surname : assignerSurnames) {
                final Assigner assigner = getController().getAssignerService().getBySurname(surname);
                assigner.getTasks().add(task);
                tmpAssigners.add(assigner);
            }
        } catch (Exception e) {
            for (Assigner executor : tmpAssigners) {
                executor.getTasks().remove(task);
            }
            throw new Exception("no such executor");
        }
        getController().getTaskService().saveTask(task);
        getController().getTaskService().showTasks();
    }

}
