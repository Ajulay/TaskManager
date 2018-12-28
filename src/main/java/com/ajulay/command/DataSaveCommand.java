package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataSaveCommand extends AbstractCommand {

    public static final String COMMAND = "/save";

    @Override
    public String getCommandKeyWord() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "save current data to file";
    }

    @Override
    public void execute() throws Exception {

        final Path dir = Paths.get("data");
        final Path path = Paths.get(dir.toString(), "AppData.txt");
        if (dir.toFile().exists()) Files.delete(dir);
        else {
            Files.createDirectories(dir);
            Files.createFile(path);
        }
        final OutputStream out = new FileOutputStream(path.toFile());
        final List<Project> projects = getController().getProjectService().getProjects();
        final List<Assigner> assigners = getController().getAssignerService().getAssigners();
        final List<Assignee> assignees = getController().getAssigneeService().findAllAssignee();
        final List<Task> tasks = getController().getTaskService().findTaskAll();
        final ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(projects);
        oos.writeObject(assignees);
        oos.writeObject(assigners);
        oos.writeObject(tasks);
        oos.close();
        out.close();
    }

}
