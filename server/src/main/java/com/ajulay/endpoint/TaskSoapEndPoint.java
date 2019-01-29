package com.ajulay.endpoint;

import com.ajulay.api.service.IOveralService;
import com.ajulay.api.soap.ITaskSoapService;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;
import com.ajulay.entity.Task;

import javax.jws.WebService;
import java.util.List;

@WebService
public class TaskSoapEndPoint implements ITaskSoapService {

    private IOveralService overalService;

    public TaskSoapEndPoint(IOveralService overalService) {
        this.overalService = overalService;
    }

    public TaskSoapEndPoint() {
    }

    @Override
    public Success saveTask(final Session session, final Task task) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Task savedTask = overalService.getTaskService().saveTask(task);
        if (savedTask == null) return null;
        return new Success();
    }

    @Override
    public Success deleteTask(final Session session, final String id) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Task deletedTask = overalService.getTaskService().deleteTask(id);
        if (deletedTask == null) return null;
        return new Success();
    }

    @Override
    public Success changeStatus(final Session session, final String taskId, final String status) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Task changedTask = overalService.getTaskService().changeStatus(taskId, status);
        if (changedTask == null) return null;
        return new Success();
    }

    @Override
    public List<Task> findTaskAll(final Session session) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getTaskService().findTaskAll();
    }

    @Override
    public List<Task> findTaskAllByProject(final Session session, final String project_id) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        List<Task> tasks = overalService.getTaskService().findTaskAllByProject(project_id);
        return tasks;
    }

    @Override
    public Task findTaskById(final Session session, String taskId) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getTaskService().findTaskById(taskId);
    }

    @Override
    public Success merge(final Session session, final List<Task> tasks) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final List<Task> mergedTasks = overalService.getTaskService().merge(tasks);
        if (mergedTasks == null || mergedTasks.isEmpty()) return null;
        return new Success();
    }

    @Override
    public List<Task> findTaskAllByUserId(final Session session, final String currentUser) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.findTaskAllByUserId(currentUser);
    }

}
