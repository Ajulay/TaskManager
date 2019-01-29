package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void execute() throws ParseException {
        System.out.println("Enter: project_id (required)");
        String in = getController().nextLine();
        final String currentUserId = getController().getSessionService().getCurrentSession().getUserId();
        final Project project = getController().getProjectService().getById(in);
        if (project == null) {
            System.out.println("No such project.");
            return;
        }
        if (!project.getAuthorId().equals(currentUserId)) {
            System.out.println("You can create task for your projects only.");
            return;
        }
        final Task task = new Task();
        task.setProjectId(in);
        System.out.println("Enter term in format: yyyy-MM-dd");
        in = getController().nextLine();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date term = dateFormat.parse(in);
        task.setTerm(term);
        System.out.println("Enter priority (1 - 3)");
        in = getController().nextLine();
        if (in == null || in.isEmpty()) {
            task.setPriority(ServiceConstant.LOW_PRIORITY);
        }
        if (in != null) {
            task.setPriority(Integer.parseInt(in));
        }
        System.out.println("Enter content");
        in = getController().nextLine();
        if (in == null || in.isEmpty()) {
            task.setContent("Enter content...");
        }
        if (in != null) {
            task.setContent(in);
        }
        addWorker(task);
        System.out.println("Task added");
        getController().getTaskService().saveTask(task);
    }

    private void addWorker(final Task task) {
        System.out.println("Enter id executor(s) (to finish write: /end)");
        final String in = getController().nextLine();
        if (ServiceConstant.END_ENTER_ASSIGNER.equals(in)) {
            return;
        }
        User worker = getController().getUserService().findById(in);
        if (worker == null) {
            System.out.println("No such executor.");
            addWorker(task);
        }

        final Assignee assignee = getController().getAssigneeService().createAssignee(task.getId(), worker.getId());
        if (assignee == null) {
            System.out.println("Problem to save information about executor.");
        }
        addWorker(task);
    }

}
