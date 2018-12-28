package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;

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
        final List<Project> projects = (List<Project>) ois.readObject();
        final List<Assigner> assigners = (List<Assigner>) ois.readObject();
        final List<Assignee> assignees = (List<Assignee>) ois.readObject();
        final List<Task> tasks = (List<Task>) ois.readObject();
        if (projects.size() > 0) System.out.println(projects.toString());
        else System.out.println("No projects");
        if (assignees.size() > 0) System.out.println(assignees.toString());
        else System.out.println("No assignees");
        if (assigners.size() > 0) System.out.println(assigners.toString());
        else System.out.println("No assigners");
        if (tasks.size() > 0) System.out.println(tasks.toString());
        else System.out.println("No tasks");
        getController().getProjectService().merge(projects);
        getController().getAssigneeService().merge(assignees);
        getController().getAssignerService().merge(assigners);
        getController().getTaskService().merge(tasks);
        ois.close();
        fis.close();
    }

}
