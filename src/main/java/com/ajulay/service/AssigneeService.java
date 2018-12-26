package com.ajulay.service;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.api.service.IAssigneeService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.dao.AssigneeDAO;
import com.ajulay.entity.Assignee;

import java.util.List;

/**
 * @inherite
 */
public class AssigneeService implements IAssigneeService {

    private final IAssigneeDAO assigneeDAO = new AssigneeDAO();

    @Override
    public Assignee createAssignee(final String taskId, final String assignerId) {
        if (taskId == null || ServiceConstant.EMPTY_VALUE.equals(taskId) ||
                assignerId == null || ServiceConstant.EMPTY_VALUE.equals(assignerId)) throw new NullPointerException();
        final Assignee assignee = new Assignee();
        assignee.setTaskId(taskId);
        assignee.setAssignerId(assignerId);
        return assigneeDAO.create(assignee);
    }

    @Override
    public Assignee deleteAssignee(final String id) throws Exception {
        if (id == null || ServiceConstant.EMPTY_VALUE.equals(id)) throw new NullPointerException();
        return assigneeDAO.delete(id);
    }

    @Override
    public Assignee updateAssignee(final Assignee assignee) throws Exception {
        if (assignee == null) throw new NullPointerException();
        return assigneeDAO.update(assignee);
    }

    @Override
    public Assignee getById(final String id) throws Exception {
        if (id == null || ServiceConstant.EMPTY_VALUE.equals(id)) throw new NullPointerException();
        return assigneeDAO.findById(id);
    }

    @Override
    public List<Assignee> AssigneeFindAll() {
        return assigneeDAO.findAll();
    }

}
