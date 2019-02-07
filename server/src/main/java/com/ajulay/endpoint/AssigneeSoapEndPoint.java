package com.ajulay.endpoint;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.ISessionService;
import com.ajulay.api.soap.IAssigneeSoapService;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Session;
import com.ajulay.exception.unchecked.NullIdException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@WebService
@Component
public class AssigneeSoapEndPoint implements IAssigneeSoapService {

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IAssigneeService assigneeService;

    @Override
    @Nullable
    public Success createAssignee(final Session session, final String taskId, final String userId) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        if (taskId == null || taskId.isEmpty() || userId == null || userId.isEmpty()) {
            throw new NullIdException();
        }
        final Assignee newAssignee = assigneeService.createAssignee(taskId, userId);
        if (newAssignee == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Success deleteAssignee(final Session session, final String id) {
        if (id == null || id.isEmpty()) {
            throw new NullIdException();
        }
        final Assignee deletedAssignee = assigneeService.removeById(id);
        if (deletedAssignee == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Success updateAssignee(final Session session, final Assignee assignee) {
        if (assignee == null) throw new NullPointerException();
        final Assignee updatedAssignee = assigneeService.update(assignee);
        if (updatedAssignee == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Assignee getById(final Session session, final String id) {
        if (id == null || id.isEmpty()) {
            throw new NullIdException();
        }
        Assignee assignee = assigneeService.findById(id);
        return assignee;
    }

    @Override
    @Nullable
    public List<Assignee> findAllAssignee(final Session session) {
        return assigneeService.findAll();
    }

    @Override
    @Nullable
    public Success merge(final Session session, final List<Assignee> assignees) {
        if (assignees == null) return null;
        final List<Assignee> mergedAssignees = assigneeService.updateAll(assignees);
        if (mergedAssignees.isEmpty()) return null;
        return new Success();
    }

    @Override
    @NotNull
    public List<Assignee> findAssigneeAllByUserId(final Session session, final String userId) {
        if (userId == null) return Collections.emptyList();
        List<Assignee> assignees = assigneeService.findAllByUserId(userId);
        return assignees;
    }

}
