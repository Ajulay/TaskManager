package com.ajulay.command;

import com.ajulay.entity.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class DataLoadCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/load";
    }

    @Override
    public String getDescription() {
        return "load data";
    }

    @Override
    public void execute() throws Exception {
        final FileInputStream fis = new FileInputStream("data/AppData.txt");
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final Domain domain = (Domain) ois.readObject();
        final List<Project> projects = domain.getProjects();
        final List<Assignee> assignees = domain.getAssignees();
        final List<User> users = domain.getUsers();
        final List<Task> tasks = domain.getTasks();
        getController().getProjectService().merge(projects);
        getController().getAssigneeService().merge(assignees);
        getController().getUserService().merge(users);
        getController().getTaskService().merge(tasks);
        ois.close();
        fis.close();
    }

}
