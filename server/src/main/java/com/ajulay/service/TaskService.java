package com.ajulay.service;

import com.ajulay.api.service.ITaskService;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;
import com.ajulay.repository.AssigneeRepository;
import com.ajulay.repository.TaskRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class TaskService implements ITaskService {

    @Inject
    @NotNull
    private TaskRepository taskRepository;

    @Inject
    @NotNull
    private AssigneeRepository assigneeRepository;

    @Inject
    @NotNull
    private EntityManager entityManager;

    @Nullable
    public Task changeStatus(@NotNull final String taskId, @NotNull final String status) {
        if (taskId.isEmpty() || status.isEmpty()) {
            return null;
        }
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Task task = taskRepository.findById(taskId);
        if (task != null) {
            task.setStatus(Status.valueOf(status.toUpperCase()));
        }
        transaction.commit();
        return task;
    }

    @Override
    @NotNull
    public List<Task> findAllByProject(@NotNull final String projectId) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //TODO remake later for Criteria
        @NotNull
        List<Task> tasks = taskRepository.findAll();
        tasks = tasks.stream().filter(e -> e.getProject().getId().equals(projectId)).collect(Collectors.toList());
        transaction.commit();
        return tasks;
    }

    @Override
    @Nullable
    public Task save(@NotNull final Task task) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Task savedTask = taskRepository.save(task);
        transaction.commit();
        return savedTask;
    }

    @Override
    @Nullable
    public Task findById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Task task = taskRepository.findById(id);
        transaction.commit();
        return task;
    }

    @Override
    @Nullable
    public Task remove(@NotNull final Task task) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Task removedTask = taskRepository.remove(task);
        transaction.commit();
        return removedTask;
    }

    @Override
    @Nullable
    public Task update(@NotNull final Task task) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Task updatedTask = taskRepository.update(task);
        transaction.commit();
        return updatedTask;
    }

    @Override
    @NotNull
    public List<Task> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Task> tasks = taskRepository.findAll();
        transaction.commit();
        return tasks;
    }

    @Override
    @NotNull
    public List<Task> updateAll(@NotNull final List<Task> tasks) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (@NotNull final Task task : tasks) {
            entityManager.merge(task);
        }
        transaction.commit();
        return tasks;
    }

    @Override
    @Nullable
    public Task removeById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable
        Task task = taskRepository.findById(id);
        if (task != null) {
            task = taskRepository.remove(task);
        }
        transaction.commit();
        return task;
    }

    @Override
    @Nullable
    public List<Task> findAllByUserId(@NotNull final String currentUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        final List<Assignee> assignees = assigneeRepository.findByUserId(currentUser);
        transaction.begin();
        final List<Task> tasks = new ArrayList<>();
        for (final Assignee assignee : assignees) {
            @Nullable final Task task = taskRepository.findById(assignee.getTaskId());
            if (task != null)
                tasks.add(task);
        }
        transaction.commit();
        return tasks;
    }

    @Override
    public List<Task> findTaskAllByUserId(String currentUserId) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Assignee> assignees = assigneeRepository.findByUserId(currentUserId);
        @NotNull final List<Task> tasks = new ArrayList<>();

        for (@NotNull final Assignee assignee : assignees) {
            Task task = null;
            try {
                task = taskRepository.findById(assignee.getTaskId());
                tasks.add(task);
            } catch (NoResultException e) {

            }
        }
        transaction.commit();
        return tasks;
    }

}
