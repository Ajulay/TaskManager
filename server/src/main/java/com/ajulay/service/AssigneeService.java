package com.ajulay.service;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.entity.Assignee;
import com.ajulay.repository.AssigneeRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
@Transactional
public class AssigneeService implements IAssigneeService {

    @Inject
    private AssigneeRepository assigneeRepository;

    @Override
    @Nullable
    public Assignee createAssignee(@NotNull final String taskId, @NotNull final String assignerId) {
        if (taskId.isEmpty() || assignerId.isEmpty()) {
            return null;
        }
        final Assignee assignee = new Assignee();
        assignee.setTaskId(taskId);
        assignee.setUserId(assignerId);
        assigneeRepository.save(assignee);
        return assignee;
    }

    @Override
    @Nullable
    public Assignee removeById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @Nullable final Assignee assignee = assigneeRepository.findById(id);
        if (assignee != null) {
            assigneeRepository.remove(assignee);
        }
        return assignee;
    }

    @Override
    @Nullable
    public Assignee update(@NotNull final Assignee assignee) {
        return assigneeRepository.save(assignee);
    }

    @Override
    @Nullable
    public Assignee findById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        return assigneeRepository.findById(id);
    }

    @Override
    @Nullable
    public List<Assignee> findAll() {
        return assigneeRepository.findAll();
    }

    @Override
    @NotNull
    public List<Assignee> findAllByUserId(@NotNull final String currentUser) {
        return assigneeRepository.findByUserId(currentUser);
    }

    @Override
    @NotNull
    public List<Assignee> findAllByTaskId(@NotNull final String taskId) {
        return assigneeRepository.findByTaskId(taskId);
    }

    @Override
    @NotNull
    public List<Assignee> removeAllByTaskId(@NotNull final String taskId) {
        if (taskId.isEmpty()) {
            return Collections.emptyList();
        }
        @NotNull final List<Assignee> assignees = assigneeRepository.findByTaskId(taskId);
        for (@NotNull final Assignee assignee : assignees) {
            assigneeRepository.remove(assignee);
        }
        return assignees;
    }

    @Override
    @Nullable
    public Assignee save(@NotNull final Assignee assignee) {
        return assigneeRepository.save(assignee);
    }

    @Override
    @Nullable
    public Assignee remove(@NotNull final Assignee assignee) {
        assigneeRepository.remove(assignee);
        return assignee;
    }

    @Override
    @NotNull
    public List<Assignee> updateAll(@NotNull final List<Assignee> assignees) {
        for (@NotNull final Assignee assignee : assignees) {
            assigneeRepository.refresh(assignee);
        }
        return assignees;
    }

}
