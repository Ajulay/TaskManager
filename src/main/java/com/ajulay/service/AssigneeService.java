package com.ajulay.service;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.api.service.IAssigneeService;
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
        final Assignee assignee = new Assignee();
        assignee.setTaskId(taskId);
        assignee.setAssignerId(assignerId);
        return assigneeDAO.create(assignee);
    }

    @Override
    public Assignee deleteAssignee(final String id) throws Exception {
        return assigneeDAO.delete(id);
    }

    @Override
    public Assignee updateAssignee(final Assignee assignee) throws Exception {
        return assigneeDAO.update(assignee);
    }

    @Override
    public Assignee getById(final String id) throws Exception {
        return assigneeDAO.findById(id);
    }

    @Override
    public List<Assignee> AssigneeFindAll() {
        return assigneeDAO.findAll();
    }

}
