package com.ajulay.dao;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.entity.Assignee;
import com.ajulay.exception.NoSuchAssigneeException;

import java.util.ArrayList;
import java.util.List;

/**
 * @inherite
 */
public class AssigneeDAO implements IAssigneeDAO {

    private final List<Assignee> assignees = new ArrayList<>();

    @Override
    public Assignee create(Assignee assignee) {
        assignees.add(assignee);
        return assignee;
    }

    @Override
    public Assignee delete(String id) throws NoSuchAssigneeException {
        for (Assignee assignee : assignees) {
            if (assignee.getId().equals(id)) {
                assignees.remove(assignee);
                return assignee;
            }
        }

        throw new NoSuchAssigneeException();
    }

    @Override
    public Assignee update(Assignee assignee) throws NoSuchAssigneeException {
        for (Assignee asee : assignees) {
            if (asee.getId().equals(assignee.getId())) {
                assignees.remove(asee);
                assignees.add(assignee);
                return assignee;
            }
        }
        throw new NoSuchAssigneeException();
    }

    @Override
    public Assignee findById(String id) throws NoSuchAssigneeException {
        for (Assignee assignee : assignees) {
            if (assignee.getId().equals(id)) {
                return assignee;
            }
        }
        throw new NoSuchAssigneeException();
    }

    @Override
    public List<Assignee> findAll() {
        return assignees;
    }

}
