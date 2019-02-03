package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.*;

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

    public void execute(@Observes TaskCreateCommand taskCreateCommand) throws Exception {
        final Session session = getController().getCurrentSession();
        final String currentUserId = getController().getCurrentSession().getUserId();
        System.out.println("Enter: project_id (required)");
        String in = getController().nextLine();
        final Project project = getController().getProjectService().getProjectSoapEndPointPort().getById(session, in);
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
        final TaskView taskView = new TaskView();
        taskView.setStatus(Status.START);
        final String taskId = UUID.randomUUID().toString();
        taskView.setId(taskId);
        taskView.setProjectId(in);
        System.out.println("Enter term in format: yyyy-MM-dd");
        in = getController().nextLine();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date term = dateFormat.parse(in);
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setGregorianChange(term);
        final XMLGregorianCalendar xmlTerm = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        taskView.setTerm(xmlTerm);
        System.out.println("Enter priority (1 - 3)");
        in = getController().nextLine();
        if (in == null || in.isEmpty()) {
            taskView.setPriority(ServiceConstant.LOW_PRIORITY);
        }
        if (in != null) {
            taskView.setPriority(Integer.parseInt(in));
        }
        System.out.println("Enter content");
        in = getController().nextLine();
        if (in == null || in.isEmpty()) {
            taskView.setContent("Enter content...");
        }
        if (in != null) {
            taskView.setContent(in);
        }
        addWorker(taskView, session);
        System.out.println("Task added");
        getController().getTaskService().getTaskSoapEndPointPort().saveTask(session, taskView);
        System.out.println("[Ok]");
        toBegin();
    }

    private void addWorker(final TaskView task, final Session session) {
        System.out.println("Enter id executor(s) (to finish write: /end)");
        final String userId = getController().nextLine();
        if (ServiceConstant.END_ENTER_ASSIGNER.equals(userId)) {
            return;
        }
        final User worker = getController().getUserService().getUserSoapEndPointPort().findById(session, userId);
        if (worker == null) {
            System.out.println("No such executor.");
            addWorker(task, session);
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
