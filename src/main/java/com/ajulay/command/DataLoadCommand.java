package com.ajulay.command;

import com.ajulay.entity.*;
import com.ajulay.enumirated.Role;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Path path = Paths.get("data/AppData.txt");
        if (!Files.exists(path)) {
            User user = getController().getUserService().createUser("admin");
            user.setRole(Role.ADMIN);
            user.setLogin("admin");
            user.setPassword("admin");
            return;
        }
        final FileInputStream fis = new FileInputStream("data/AppData.txt");
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final Domain domain = (Domain) ois.readObject();
        final List<Project> projects = domain.getProjects();
        final List<Assignee> assignees = domain.getAssignees();
        final List<User> assigners = domain.getAssigners();
        final List<Task> tasks = domain.getTasks();
        getController().getProjectService().merge(projects);
        getController().getAssigneeService().merge(assignees);
        getController().getUserService().merge(assigners);
        getController().getTaskService().merge(tasks);
        ois.close();
        fis.close();
    }

}
