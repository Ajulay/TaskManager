package com.ajulay.service;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.api.service.IAssigneeService;
import com.ajulay.dao.AssigneeDAO;
import com.ajulay.entity.Assignee;
import com.ajulay.exception.checked.NoSuchAssigneeException;
import com.ajulay.exception.unchecked.NullIdException;

import java.util.List;

/**
 * {@inheritDoc}
 */
public class AssigneeService implements IAssigneeService {

    private final IAssigneeDAO assigneeDAO = new AssigneeDAO();

    @Override
    public Assignee createAssignee(final String taskId, final String assignerId) {
        if (taskId.isEmpty() || assignerId.isEmpty()) {
            throw new NullIdException();
        }
        final Assignee assignee = new Assignee();
        assignee.setTaskId(taskId);
        assignee.setAssignerId(assignerId);
        return assigneeDAO.create(assignee);
    }

    @Override
    public Assignee deleteAssignee(final String id) throws NoSuchAssigneeException {
        if (id.isEmpty()) {
            throw new NullIdException();
        }
        return assigneeDAO.delete(id);
    }

    @Override
    public Assignee updateAssignee(final Assignee assignee) throws NoSuchAssigneeException {
        if (assignee == null) throw new NullPointerException();
        return assigneeDAO.update(assignee);
    }

    @Override
    public Assignee getById(final String id) throws NoSuchAssigneeException {
        if (id.isEmpty()) {
            throw new NullIdException();
        }
        return assigneeDAO.findById(id);
    }

    @Override
    public List<Assignee> findAllAssignee() {
        return assigneeDAO.findAll();
    }

}
