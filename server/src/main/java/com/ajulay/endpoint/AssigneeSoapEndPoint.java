package com.ajulay.endpoint;

import com.ajulay.api.service.IOveralService;
import com.ajulay.api.soap.IAssigneeSoapService;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Session;
import com.ajulay.exception.unchecked.NullIdException;

import javax.jws.WebService;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@WebService
public class AssigneeSoapEndPoint implements IAssigneeSoapService {

    private IOveralService overalService;

    public AssigneeSoapEndPoint(IOveralService overalService) {
        this.overalService = overalService;
    }

    public AssigneeSoapEndPoint() {
    }

    @Override
    public Success createAssignee(final Session session, final String taskId, final String userId) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        if (taskId == null || taskId.isEmpty() || userId == null || userId.isEmpty()) {
            throw new NullIdException();
        }
        final Assignee newAssignee = overalService.getAssigneeService().createAssignee(taskId, userId);
        if (newAssignee == null) return null;
        return new Success();
    }

    @Override
    public Success deleteAssignee(final Session session, final String id) {
        if (id == null || id.isEmpty()) {
            throw new NullIdException();
        }
        final Assignee deletedAssignee = overalService.getAssigneeService().deleteAssignee(id);
        if (deletedAssignee == null) return null;
        return new Success();
    }

    @Override
    public Success updateAssignee(final Session session, final Assignee assignee) {
        if (assignee == null) throw new NullPointerException();
        final Assignee updatedAssignee = overalService.getAssigneeService().updateAssignee(assignee);
        if (updatedAssignee == null) return null;
        return new Success();
    }

    @Override
    public Assignee getById(final Session session, final String id) {
        if (id == null || id.isEmpty()) {
            throw new NullIdException();
        }
        return overalService.getAssigneeService().getById(id);
    }

    @Override
    public List<Assignee> findAllAssignee(final Session session) {
        return overalService.getAssigneeService().findAllAssignee();
    }

    @Override
    public Success merge(final Session session, final List<Assignee> assignees) {
        if (assignees == null) return null;
        final List<Assignee> mergedAssignees = overalService.getAssigneeService().merge(assignees);
        if (mergedAssignees == null) return null;
        return new Success();
    }

    @Override
    public List<Assignee> findAssigneeAllByUserId(final Session session, final String userId) {
        if (userId == null) return Collections.emptyList();
        return overalService.getAssigneeService().findAssigneeAllByUserId(userId);
    }

}
