package com.ajulay.command;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.checked.NoSuchProjectException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class TaskCreateCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/tsk";
    }

    @Override
    public String getDescription() {
        return "add new task";
    }

    @Override
    public void execute() throws NoSuchProjectException, NoSuchAssignerException {

        System.out.println("Enter: project_id (required)");
        String in = getController().nextLine();
        if (in == null || getController().getProjectService().getById(in) == null) return;
        final Task task = new Task();
        task.setProjectId(in);
        System.out.println("Enter term in format: yyyy-MM-dd");
        in = getController().nextLine();
        Instant term = null;
        if (in.isEmpty()) {
            term = Instant.now();
        } else {
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
        in = getController().nextLine();
        if (in.isEmpty()) {
            task.setPriority(ServiceConstant.LOW_PRIORITY);
        } else {
            task.setPriority(Integer.parseInt(in));
        }
        System.out.println("Enter content");
        in = getController().nextLine();
        if (in.isEmpty()) {
            task.setContent("Enter content...");
        } else {
            task.setContent(in);
        }
        System.out.println("Enter executor(s) (to finish write /end)");
        while (!ServiceConstant.END_ENTER_ASSIGNER.equals((in = getController().nextLine()))) {
            User assigner = getController().getUserService().getBySurname(in);
            getController().getAssigneeService().createAssignee(task.getId(), assigner.getId());
        }
        System.out.println("Task added");
        getController().getTaskService().saveTask(task);
        getController().getTaskService().findTaskAll();
    }

}
