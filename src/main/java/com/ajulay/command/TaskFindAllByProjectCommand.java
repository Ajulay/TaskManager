package com.ajulay.command;

import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.exception.checked.NoSuchProjectException;
import com.ajulay.exception.checked.NoSuchTaskException;

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
    public void execute() throws NoSuchProjectException, NoSuchTaskException {
        System.out.println("Enter project id...");
        final String projectId = getController().nextLine();
        System.out.println("Project id: " + projectId);
        final User user = getController().getCurrentUser();
        if (Role.MANAGER.equals(user.getRole())) {
            final Project project = getController().getProjectService().getById(projectId);
            if (!user.getId().equals(project.getAuthorId())) {
                throw new NoSuchTaskException("Manager can see task of his projects only");
            }
        }
        int index = 1;
        for (final Task task : getController().getTaskService().findTaskAllByProject(projectId)) {
            final String term = task.getTerm().toString().substring(0, 10);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
    }

}
