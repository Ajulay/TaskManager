package com.ajulay.command;

import com.ajulay.entity.Task;

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
        System.out.println("Enter project id...");
        final String projectId = getController().nextLine();
        int index = 1;
        System.out.println("Project id: " + projectId);
        for (Task task : getController().getTaskService().findTaskAllByProject(projectId)) {
            System.out.println(index++ + ". Task term: " + task.getTerm() + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
    }

}
