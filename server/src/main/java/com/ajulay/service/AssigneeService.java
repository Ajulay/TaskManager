package com.ajulay.service;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.entity.Assignee;
import com.ajulay.repository.AssigneeRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class AssigneeService implements IAssigneeService {

    @Inject
    @NotNull
    private AssigneeRepository assigneeRepository;

    @Inject
    @NotNull
    private EntityManager entityManager;


    @Override
    @Nullable
    public Assignee createAssignee(@NotNull final String taskId, @NotNull final String assignerId) {
        if (taskId.isEmpty() || assignerId.isEmpty()) {
            return null;
        }
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final Assignee assignee = new Assignee();
        assignee.setTaskId(taskId);
        assignee.setUserId(assignerId);
        assigneeRepository.save(assignee);
        transaction.commit();
        return assignee;
    }

    @Override
    @Nullable
    public Assignee removeById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Assignee assignee = assigneeRepository.findById(id);
        if (assignee != null) {
            assigneeRepository.remove(assignee);
        }
        transaction.commit();
        return assignee;
    }

    @Override
    @Nullable
    public Assignee update(@NotNull final Assignee assignee) {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Assignee updatedAssignee = assigneeRepository.update(assignee);
        transaction.commit();
        return updatedAssignee;
    }

    @Override
    @Nullable
    public Assignee findById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Assignee assignee = assigneeRepository.findById(id);
        transaction.commit();
        return assignee;
    }

    @Override
    @Nullable
    public List<Assignee> findAll() {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final List<Assignee> assignees = assigneeRepository.findAll();
        transaction.commit();
        return assignees;
    }

    @Override
    @NotNull
    public List<Assignee> findAllByUserId(@NotNull final String currentUser) {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Assignee> assignees = assigneeRepository.findByUserId(currentUser);
        transaction.commit();
        return assignees;
    }

    @Override
    @NotNull
    public List<Assignee> findAllByTaskId(@NotNull final String taskId) {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Assignee> assignees = assigneeRepository.findByTaskId(taskId);
        transaction.commit();
        return assignees;
    }

    @Override
    @NotNull
    public List<Assignee> removeAllByTaskId(@NotNull final String taskId) {
        if (taskId.isEmpty()) {
            return Collections.emptyList();
        }
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Assignee> assignees = assigneeRepository.findByTaskId(taskId);
        for (@NotNull final Assignee assignee : assignees) {
            assigneeRepository.remove(assignee);
        }
        transaction.commit();
        return assignees;
    }

    @Override
    @Nullable
    public Assignee save(@NotNull final Assignee assignee) {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Assignee savedAssignee = assigneeRepository.save(assignee);
        transaction.commit();
        return savedAssignee;
    }

    @Override
    @Nullable
    public Assignee remove(@NotNull final Assignee assignee) {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Assignee removedAssignee = assigneeRepository.remove(assignee);
        transaction.commit();
        return removedAssignee;
    }

    @Override
    @NotNull
    public List<Assignee> updateAll(@NotNull final List<Assignee> assignees) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (@NotNull final Assignee assignee : assignees) {
            entityManager.merge(assignee);
        }
        transaction.commit();
        return assignees;
    }

}
