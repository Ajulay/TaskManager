package com.ajulay.dao;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.entity.Assignee;


import java.util.ArrayList;
import java.util.List;


public class AssigneeDAO implements IAssigneeDAO {

    private final List<Assignee> assignees = new ArrayList<>();

    @Override
    public Assignee create(Assignee assignee) {
        assignees.add(assignee);
        return assignee;
    }

    @Override
    public Assignee delete(String id) throws Exception {
        for (Assignee assignee : assignees) {
            if (assignee.getId().equals(id)) {
                assignees.remove(assignee);
                return assignee;
            }
        }

        throw new Exception("no such assignee");
    }

    @Override
    public Assignee update(Assignee assignee) throws Exception {
        for (Assignee asee : assignees) {
            if (asee.getId().equals(assignee.getId())) {
                assignees.remove(asee);
                assignees.add(assignee);
                return assignee;
            }
        }
        throw new Exception("no such assignee");
    }

    @Override
    public Assignee findById(String id) throws Exception {
        for (Assignee assignee : assignees) {
            if (assignee.getId().equals(id)) {
                return assignee;
            }
        }
        throw new Exception("no such assignee");
    }

    @Override
    public List<Assignee> findAll() {
        return assignees;
    }

}
