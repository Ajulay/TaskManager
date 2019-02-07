package com.ajulay.service;

import com.ajulay.api.service.ITaskService;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;
import com.ajulay.repository.AssigneeRepository;
import com.ajulay.repository.TaskRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Component
@Transactional
public class TaskService implements ITaskService {

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private AssigneeRepository assigneeRepository;

    @Nullable
    public Task changeStatus(@NotNull final String taskId, @NotNull final String status) {
        if (taskId.isEmpty() || status.isEmpty()) {
            return null;
        }
        @Nullable final Task task = taskRepository.findById(taskId).get();
        if (task != null) {
            task.setStatus(Status.valueOf(status.toUpperCase()));
        }
        return task;
    }

    @NotNull
    public List<Task> findAllByProject(@NotNull final String projectId) {
        @NotNull final List<Task> tasks = taskRepository.findAllByProject(projectId);
        return tasks;
    }

    @Nullable
    public Task save(@NotNull final Task task) {
        return taskRepository.save(task);
    }

    @Nullable
    public Task findById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        return taskRepository.findById(id).get();
    }

    @Nullable
    public Task remove(@NotNull final Task task) {
        taskRepository.delete(task);
        return task;
    }

    @Nullable
    public Task update(@NotNull final Task task) {
        taskRepository.save(task);
        return task;
    }

    @NotNull
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @NotNull
    public List<Task> updateAll(@NotNull final List<Task> tasks) {
        for (@NotNull final Task task : tasks) {
            taskRepository.save(task);
        }
        return tasks;
    }

    @Nullable
    public Task removeById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @Nullable final Task task = taskRepository.findById(id).get();
        if (task != null) {
            taskRepository.delete(task);
        }
        return task;
    }

    @Nullable
    public List<Task> findAllByUserId(@NotNull final String currentUser) {
        @NotNull final List<Assignee> assignees = assigneeRepository.findByUserId(currentUser);
        final List<Task> tasks = new ArrayList<>();
        for (@NotNull final Assignee assignee : assignees) {
            @Nullable final Task task = taskRepository.findById(assignee.getTaskId()).get();
            if (task != null)
                tasks.add(task);
        }
        return tasks;
    }

    public List<Task> findTaskAllByUserId(String currentUserId) {
        @NotNull final List<Assignee> assignees = assigneeRepository.findByUserId(currentUserId);
        final List<Task> tasks = new ArrayList<>();
        for (@NotNull final Assignee assignee : assignees) {
            Task task = null;
            try {
                task = taskRepository.findById(assignee.getTaskId()).get();
                tasks.add(task);
            } catch (NoResultException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

}
