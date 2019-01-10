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
        final List<Project> projects = getController().getProjectService().getProjects();
        final User user = getController().getUserService().getCurrentUser();
        final List<Task> resultTasks = new ArrayList<>();
        List<Task> tasks = getController().getTaskService().findTaskAll();
        if (Role.MANAGER.equals(user.getRole())) {
            final List<Project> tmpProjects = new ArrayList<>();
            for (final Project project : projects) {
                if (project.getAuthorId().equals(user.getId())) {
                    tmpProjects.add(project);
                }
            }
            for (final Project project : tmpProjects) {
                final List<Task> actualTasks = getController().getTaskService().findTaskAllByProject(project.getId());
                resultTasks.addAll(actualTasks);
            }
            tasks = resultTasks;
        } else if (Role.WORKER.equals(user.getRole())) {
            final List<Assignee> allAssignees = getController().getAssigneeService().findAllAssignee();
            for (final Assignee assignee : allAssignees) {
                if (assignee.getAssignerId().equals(user.getId())) {
                    final Task task = getController().getTaskService().findTaskById(assignee.getTaskId());
                    resultTasks.add(task);
                }
            }
            tasks = resultTasks;
        }
        int index = 1;
        for (final Task task : tasks) {
            final String term = task.getTerm().toString().substring(0, 10);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent() + ", status: " + task.getStatus().toString());
        }
    }

}
