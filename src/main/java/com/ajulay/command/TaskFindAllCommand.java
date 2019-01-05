package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.checked.NoSuchTaskException;

import java.util.ArrayList;
import java.util.List;

public class TaskFindAllCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/tks";
    }

    @Override
    public String getDescription() {
        return "show all tasks";
    }

    @Override
    public void execute() throws NoSuchTaskException {
        List<Project> projects = getController().getProjectService().getProjects();
        final User user = getController().getCurrentUser();
        List<Task> tasks = getController().getTaskService().findTaskAll();
        final List<Task> tmpTasks = new ArrayList<>();
        if (Role.MANAGER.equals(user.getRole())) {
            final List<Project> tmpProjects = new ArrayList<>();
            for (Project project : projects) {
                if (project.getAuthorId().equals(user.getId())) {
                    tmpProjects.add(project);
                }
            }

            for (final Project project : tmpProjects) {
                tmpTasks.addAll(getController().getTaskService().findTaskAllByProject(project.getId()));
            }
            tasks = tmpTasks;
        }
        if (Role.WORKER.equals(user.getRole())) {
            final List<Assignee> allAssignees = getController().getAssigneeService().findAllAssignee();
            for (Assignee assignee : allAssignees) {
                if (assignee.getAssignerId().equals(user.getId())) {
                    final Task task = getController().getTaskService().findTaskById(assignee.getTaskId());
                    tmpTasks.add(task);
                }
            }
            tasks = tmpTasks;
        }

        int index = 1;
        for (Task task : tasks) {
            System.out.println(index++ + ". Task term: " + task.getTerm() + ", task id: " + task.getId() +
                    ", task content: " + task.getContent() + ", status: " + task.getStatus().toString());

        }
    }
}
