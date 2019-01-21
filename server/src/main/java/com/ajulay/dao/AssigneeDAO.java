package com.ajulay.dao;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.entity.Assignee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Assignee delete(final String id) {
        for (final Assignee assignee : assignees) {
            if (id.equals(assignee.getId())) {
                assignees.remove(assignee);
                return assignee;
            }
        }
        return null;
    }

    @Override
    public Assignee update(final Assignee assignee) {
        for (final Assignee asee : assignees) {
            if (asee.getId().equals(assignee.getId())) {
                assignees.remove(asee);
                assignees.add(assignee);
                return assignee;
            }
        }
        return null;
    }

    @Override
    public Assignee findById(final String id) {
        for (final Assignee assignee : assignees) {
            if (id.equals(assignee.getId())) {
                return assignee;
            }
        }
        return null;
    }

    @Override
    public List<Assignee> findAll() {
        return assignees;
    }

    @Override
    public List<Assignee> merge(List<Assignee> assignees) {
        this.assignees.clear();
        this.assignees.addAll(assignees);
        return assignees;
    }

    @Override
    public List<Assignee> findByUserId(final String userId) {
        return assignees.stream().filter(assignee -> assignee.getAssignerId().equals(userId))
                .collect(Collectors.toList());
    }

}
