package com.ajulay.service;

import com.ajulay.api.service.ITaskService;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;
import com.ajulay.repository.AssigneeRepository;
import com.ajulay.repository.TaskRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
@Transactional
public class TaskService implements ITaskService {

    @Inject
    @NotNull
    private TaskRepository taskRepository;

    @Inject
    @NotNull
    private AssigneeRepository assigneeRepository;

    @Nullable
    public Task changeStatus(@NotNull final String taskId, @NotNull final String status) {
        if (taskId.isEmpty() || status.isEmpty()) {
            return null;
        }
        @Nullable final Task task = taskRepository.findBy(taskId);
        if (task != null) {
            task.setStatus(Status.valueOf(status.toUpperCase()));
        }
        return task;
    }

    @Override
    @NotNull
    public List<Task> findAllByProject(@NotNull final String projectId) {
        @NotNull
        List<Task> tasks = taskRepository.findAllByProject(projectId);
        return tasks;
    }

    @Override
    @Nullable
    public Task save(@NotNull final Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Nullable
    public Task findById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        return taskRepository.findBy(id);
    }

    @Override
    @Nullable
    public Task remove(@NotNull final Task task) {
        taskRepository.remove(task);
        return task;
    }

    @Override
    @Nullable
    public Task update(@NotNull final Task task) {
        taskRepository.refresh(task);
        return task;
    }

    @Override
    @NotNull
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @NotNull
    public List<Task> updateAll(@NotNull final List<Task> tasks) {
        for (@NotNull final Task task : tasks) {
            taskRepository.refresh(task);
        }
        return tasks;
    }

    @Override
    @Nullable
    public Task removeById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @Nullable final Task task = taskRepository.findBy(id);
        if (task != null) {
            taskRepository.remove(task);
        }
        return task;
    }

    @Override
    @Nullable
    public List<Task> findAllByUserId(@NotNull final String currentUser) {
        @NotNull final List<Assignee> assignees = assigneeRepository.findByUserId(currentUser);
        final List<Task> tasks = new ArrayList<>();
        for (@NotNull final Assignee assignee : assignees) {
            @Nullable final Task task = taskRepository.findBy(assignee.getTaskId());
            if (task != null)
                tasks.add(task);
        }
        return tasks;
    }

    @Override
    public List<Task> findTaskAllByUserId(String currentUserId) {
        @NotNull final List<Assignee> assignees = assigneeRepository.findByUserId(currentUserId);
        final List<Task> tasks = new ArrayList<>();

        for (@NotNull final Assignee assignee : assignees) {
            Task task = null;
            try {
                task = taskRepository.findBy(assignee.getTaskId());
                tasks.add(task);
            } catch (NoResultException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

}
