package com.ajulay.command;


import com.ajulay.api.IController;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class TaskCreateCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/tsk";
    }

    @Override
    public String getDescription() {
        return "add new task";
    }

    public void execute(@Observes @Nullable TaskCreateCommand taskCreateCommand) throws Exception {
        @Nullable final IController controller = getController();
        if (controller == null) {
            System.out.println("Something wrong... Try again.");
            toBegin();
            return;
        }
        @Nullable final Session session = controller.getCurrentSession();
        if (session == null) {
            System.out.println("You are log out.");
            toBegin();
            return;
        }
        @Nullable final String currentUserId = controller.getCurrentSession().getUserId();
        if (currentUserId == null || currentUserId.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        System.out.println("Enter: project_id (required)");
        @Nullable final String projectId = controller.nextLine();
        if (projectId == null || projectId.isEmpty()) {
            System.out.println("You are log out");
            toBegin();
            return;
        }
        final Project project = getController().getProjectService().getProjectSoapEndPointPort().getById(session, projectId);
        if (project == null) {
            System.out.println("No such project.");
            toBegin();
            return;
        }
        if (!project.getAuthorId().equals(currentUserId)) {
            System.out.println("You can create task for your projects only.");
            toBegin();
            return;
        }
        @NotNull final TaskView taskView = new TaskView();
        taskView.setStatus(Status.START);
        @NotNull final String taskId = UUID.randomUUID().toString();
        taskView.setId(taskId);
        taskView.setProjectId(projectId);
        System.out.println("Enter term in format: yyyy-MM-dd");
        @Nullable final String stringTerm = getController().nextLine();
        @NotNull final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        @NotNull final Date term = dateFormat.parse(stringTerm);
        @NotNull final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setGregorianChange(term);
        @NotNull final XMLGregorianCalendar xmlTerm = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        taskView.setTerm(xmlTerm);
        System.out.println("Enter priority (1 - 3)");
        @Nullable final String priority = controller.nextLine();
        if (priority == null || priority.isEmpty()) {
            taskView.setPriority(ServiceConstant.LOW_PRIORITY);
        }
        if (priority != null) {
            taskView.setPriority(Integer.parseInt(priority));
        }
        System.out.println("Enter content");
        @Nullable final String content = getController().nextLine();
        if (content == null || content.isEmpty()) {
            taskView.setContent("Enter content...");
        }
        if (content != null) {
            taskView.setContent(priority);
        }
        addWorker(taskView, session);
        System.out.println("Task added");
        getController().getTaskService().getTaskSoapEndPointPort().saveTask(session, taskView);
        System.out.println("[Ok]");
        toBegin();
    }

    private void addWorker(@NotNull final TaskView task, @NotNull final Session session) {
        System.out.println("Enter id executor(s) (to finish write: /end)");
        @Nullable final String userId = getController().nextLine();
        if (userId == null || userId.isEmpty()) {
            addWorker(task, session);
            return;
        }
        if (ServiceConstant.END_ENTER_ASSIGNER.equals(userId)) {
            return;
        }
        @Nullable final User worker = getController().getUserService().getUserSoapEndPointPort().findById(session, userId);
        if (worker == null) {
            System.out.println("No such executor.");
            addWorker(task, session);
            return;
        }
        System.out.println("worker id: " + worker.getId());
        final Success success = getController().getAssigneeService()
                .getAssigneeSoapEndPointPort().createAssignee(session, task.getId(), worker.getId());
        if (success == null) {
            System.out.println("Executor not set. Try again.");
        }
        addWorker(task, session);
    }

}
