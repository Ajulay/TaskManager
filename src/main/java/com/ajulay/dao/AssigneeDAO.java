package com.ajulay.dao;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.entity.Assignee;
import com.ajulay.exception.checked.NoSuchAssigneeException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class AssigneeDAO implements IAssigneeDAO {

    private final List<Assignee> assignees = new ArrayList<>();

    @Override
    public Assignee create(final Assignee assignee) {
        assignees.add(assignee);
        return assignee;
    }

    @Override
    public Assignee delete(final String id) throws NoSuchAssigneeException {
        for (final Assignee assignee : assignees) {
            if (id.equals(assignee.getId())) {
                assignees.remove(assignee);
                return assignee;
            }
        }

        throw new NoSuchAssigneeException();
    }

    @Override
    public Assignee update(final Assignee assignee) throws NoSuchAssigneeException {
        for (final Assignee asee : assignees) {
            if (asee.getId().equals(assignee.getId())) {
                assignees.remove(asee);
                assignees.add(assignee);
                return assignee;
            }
        }
        throw new NoSuchAssigneeException();
    }

    @Override
    public Assignee findById(final String id) throws NoSuchAssigneeException {
        for (final Assignee assignee : assignees) {
            if (id.equals(assignee.getId())) {
                return assignee;
            }
        }
        throw new NoSuchAssigneeException();
    }

    @Override
    public List<Assignee> findAll() {
        return assignees;
    }

    @Override
    public boolean merge(List<Assignee> assignees) {
        this.assignees.clear();
        this.assignees.addAll(assignees);
        return true;
    }

}
