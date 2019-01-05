package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
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
        final List<Project> projects = (List<Project>) ois.readObject();
        final List<Assignee> assignees = (List<Assignee>) ois.readObject();
        final List<User> assigners = (List<User>) ois.readObject();
        final List<Task> tasks = (List<Task>) ois.readObject();
        getController().getProjectService().merge(projects);
        getController().getAssigneeService().merge(assignees);
        getController().getUserService().merge(assigners);
        getController().getTaskService().merge(tasks);
        ois.close();
        fis.close();
    }

}
