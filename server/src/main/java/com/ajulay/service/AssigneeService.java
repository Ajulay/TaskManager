package com.ajulay.service;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.api.service.IAssigneeService;
import com.ajulay.dao.AssigneeDAO;
import com.ajulay.entity.Assignee;
import com.ajulay.exception.unchecked.NullIdException;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class AssigneeService implements IAssigneeService {

    private final IAssigneeDAO assigneeDAO = new AssigneeDAO();


    @Override
    public Assignee createAssignee(final String taskId, final String assignerId) {
        if (taskId == null || taskId.isEmpty() || assignerId == null || assignerId.isEmpty()) {
            throw new NullIdException();
        }
        final Assignee assignee = new Assignee();
        assignee.setTaskId(taskId);
        assignee.setUserId(assignerId);
        return assigneeDAO.create(assignee);
    }

    @Override
    public Assignee deleteAssignee(final String id) {
        if (id == null || id.isEmpty()) {
            throw new NullIdException();
        }
        return assigneeDAO.delete(id);
    }

    @Override
    public Assignee updateAssignee(final Assignee assignee) {
        if (assignee == null) throw new NullPointerException();
        return assigneeDAO.update(assignee);
    }

    @Override
    public Assignee getById(final String id) {
        if (id == null || id.isEmpty()) {
            throw new NullIdException();
        }
        return assigneeDAO.findById(id);
    }

    @Override
    public List<Assignee> findAllAssignee() {
        return assigneeDAO.findAll();
    }

    @Override
    public List<Assignee> merge(final List<Assignee> assignees) {
        if (assignees == null) return null;
        return assigneeDAO.merge(assignees);
    }

    @Override
    public List<Assignee> findAssigneeAllByUserId(final String userId) {
        if (userId == null) return Collections.emptyList();
        return assigneeDAO.findByUserId(userId);
    }

    public IAssigneeDAO getAssigneeDAO() {
        return assigneeDAO;
    }

    @Override
    public List<Assignee> deleteAssigneeAllByTaskId(final String taskId) {
        final List<Assignee> assignees = findAssigneeAllByTaskId(taskId);
        for (final Assignee assignee : assignees) {
            deleteAssignee(assignee.getId());
        }
        return assignees;
    }

    private List<Assignee> findAssigneeAllByTaskId(final String taskId) {
        if (taskId == null) return Collections.emptyList();
        return assigneeDAO.findByTaskId(taskId);
    }

}
