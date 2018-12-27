package com.ajulay.service;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.api.service.IAssigneeService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.dao.AssigneeDAO;
import com.ajulay.entity.Assignee;
import com.ajulay.exception.checked.NoSuchAssigneeException;
import com.ajulay.exception.unchecked.NullIdException;

import java.util.List;

/**
 * @inherite
 */
public class AssigneeService implements IAssigneeService {

    private final IAssigneeDAO assigneeDAO = new AssigneeDAO();

    @Override
    public Assignee createAssignee(final String taskId, final String assignerId) {
        if (taskId == null || assignerId == null ||
                ServiceConstant.EMPTY_VALUE.equals(taskId) ||
                ServiceConstant.EMPTY_VALUE.equals(assignerId)) {
            throw new NullIdException();
        }
        final Assignee assignee = new Assignee();
        assignee.setTaskId(taskId);
        assignee.setAssignerId(assignerId);
        return assigneeDAO.create(assignee);
    }

    @Override
    public Assignee deleteAssignee(final String id) throws NoSuchAssigneeException {
        if (id == null || ServiceConstant.EMPTY_VALUE.equals(id)) {
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
        if (id == null || ServiceConstant.EMPTY_VALUE.equals(id)) {
            throw new NullIdException();
        }
        return assigneeDAO.findById(id);
    }

    @Override
    public List<Assignee> findAllAssignee() {
        return assigneeDAO.findAll();
    }

}
