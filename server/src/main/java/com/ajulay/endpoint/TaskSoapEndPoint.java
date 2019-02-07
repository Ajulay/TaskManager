package com.ajulay.endpoint;

import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ISessionService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.api.soap.ITaskSoapService;
import com.ajulay.dto.TaskView;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Project;
import com.ajulay.entity.Session;
import com.ajulay.entity.Task;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
@Component
public class TaskSoapEndPoint implements ITaskSoapService {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IProjectService projectService;

    @Override
    @Nullable
    public Success saveTask(final Session session, final TaskView taskView) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Task task = taskMapper(taskView);
        final Task savedTask = taskService.save(task);
        if (savedTask == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Success deleteTask(final Session session, final String id) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Task deletedTask = taskService.removeById(id);
        if (deletedTask == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Success changeStatus(final Session session, final String taskId, final String status) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Task changedTask = taskService.changeStatus(taskId, status);
        if (changedTask == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public List<TaskView> findTaskAll(final Session session) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        List<Task> tasks = taskService.findAll();


        return taskMapper((Task[]) tasks.toArray());
    }

    @Override
    @Nullable
    public List<TaskView> findTaskAllByProject(final Session session, final String project_id) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        @Nullable final Project project = projectService.findById(project_id);
        if (project == null) return null;
        if (!project.getAuthorId().equals(session.getUserId())) return null;
        final List<Task> tasks = taskService.findAllByProject(project_id);
        return taskMapper(tasks.toArray(new Task[0]));
    }

    @Override
    @Nullable
    public TaskView findTaskById(final Session session, String taskId) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        Task task = taskService.findById(taskId);
        return taskMapper(task);
    }


    @Nullable
    public Success merge(final Session session, final List<TaskView> taskViews) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final List<Task> tasks = taskMapper((TaskView[]) taskViews.toArray());
        final List<Task> mergedTasks = taskService.updateAll(tasks);
        if (mergedTasks.isEmpty()) return null;
        return new Success();
    }

    @Override
    @Nullable
    public List<TaskView> findTaskAllByUserId(final Session session, final String currentUser) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        List<Task> tasks = taskService.findAllByUserId(currentUser);
        return taskMapper(tasks.toArray(new Task[0]));
    }

    private List<TaskView> taskMapper(Task[] tasks) {
        List<TaskView> taskViews = new ArrayList<>();
        for (final Task task : tasks) {
            final TaskView taskView = taskMapper(task);
            taskViews.add(taskView);
        }
        return taskViews;
    }

    private TaskView taskMapper(final Task task) {
        final TaskView taskView = new TaskView();
        taskView.setId(task.getId());
        taskView.setProjectId(task.getProject().getId());
        taskView.setContent(task.getContent());
        taskView.setPriority(task.getPriority());
        taskView.setStatus(task.getStatus());
        taskView.setTerm(task.getTerm());
        return taskView;
    }

    private List<Task> taskMapper(TaskView[] taskViews) {
        List<Task> tasks = new ArrayList<>();
        for (final TaskView taskView : taskViews) {
            final Task task = taskMapper(taskView);
            tasks.add(task);
        }
        return tasks;
    }

    private Task taskMapper(final TaskView taskView) {
        final Task task = new Task();
        task.setId(taskView.getId());
        final Project project = projectService.findById(taskView.getProjectId());
        task.setProject(project);
        task.setContent(taskView.getContent());
        task.setPriority(taskView.getPriority());
        task.setStatus(taskView.getStatus());
        task.setTerm(taskView.getTerm());
        return task;
    }

}
