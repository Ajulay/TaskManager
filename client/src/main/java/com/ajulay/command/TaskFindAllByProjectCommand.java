package com.ajulay.command;


import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.TaskView;

import javax.enterprise.event.Observes;
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

    public void execute(@Observes TaskFindAllByProjectCommand taskFindAllByProjectCommand) {
        final Session session = getController().getCurrentSession();
        System.out.println("Enter project id...");
        final String projectId = getController().nextLine();
        final List<TaskView> tasks = getController().getTaskService().getTaskSoapEndPointPort()
                .findTaskAllByProject(session, projectId);
        int index = 1;
        for (final TaskView task : tasks) {
            final String term = task.getTerm().toString().substring(0, ServiceConstant.SUBSTRING_INSTANT);
            System.out.println(index++ + ". Task term: " + term + ", task id: " + task.getId() +
                    ", task content: " + task.getContent());
        }
        System.out.println("[Ok]");
        toBegin();
    }

    private void toBegin() {
        getAbstractCommandEvent().fire(getController().getBeginCommand());
    }
}
