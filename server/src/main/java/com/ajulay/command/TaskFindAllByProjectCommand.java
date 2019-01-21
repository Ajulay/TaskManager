package com.ajulay.command;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;

import java.util.List;

public class TaskFindAllByProjectCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/ptks";
    }

    @Override
    public String getDescription() {
        return "show tasks in project";
    }

    @Override
    public void execute() {
        final String currentUserId = getController().getSessionService().getCurrentSession().getUserId();
        System.out.println("Enter project id...");
        final String projectId = getController().nextLine();
        final Project project = getController().getProjectService().getById(projectId);
        if (project == null) {
            System.out.println("No such project");
            return;
        }
        if (!currentUserId.equals(project.getAuthorId())) {
            System.out.println("You can see tasks of your projects only.");
            return;
        }
        System.out.println("Project name: " + project.getName());
        final List<Task> tasks = getController().getTaskService().findTaskAllByProject(projectId);
        int index = 1;
        for (final Task task : tasks) {
            final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
    }

}
