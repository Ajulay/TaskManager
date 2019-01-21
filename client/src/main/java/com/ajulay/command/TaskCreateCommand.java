package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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
    public void execute() throws Exception {
        final Session session = getController().getCurrentSession();
        final String currentUserId = getController().getCurrentSession().getUserId();
        System.out.println("Enter: project_id (required)");
        String in = getController().nextLine();
        final Project project = getController().getProjectService().getProjectSoapEndPointPort().getById(session, in);
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
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setGregorianChange(term);
        final XMLGregorianCalendar xmlTerm = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        task.setTerm(xmlTerm);
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
        addWorker(task, session);
        System.out.println("Task added");
        getController().getTaskService().getTaskSoapEndPointPort().saveTask(session, task);
    }

    private void addWorker(final Task task, final Session session) {
        System.out.println("Enter surname executor(s) (to finish write: /end)");
        final String surname = getController().nextLine();
        if (ServiceConstant.END_ENTER_ASSIGNER.equals(surname)) {
            return;
        }
        final User worker = getController().getUserService().getUserSoapEndPointPort().getBySurname(session, surname);
        if (worker == null) {
            System.out.println("No such executor.");
            addWorker(task, session);
        }
        final Success success = getController().getAssigneeService()
                .getAssigneeSoapEndPointPort().createAssignee(session, task.getId(), worker.getId());
        if (success == null) {
            System.out.println("Executor not set. Try again.");
        }
        addWorker(task, session);
    }

}
