package com.ajulay.api.dao;

import com.ajulay.entity.Assignee;

import java.sql.Connection;
import java.util.List;

/**
 * IAssigneeDAO defines base data access methods for Assignee
 */
public interface IAssigneeDAO {

    Assignee create(Assignee assignee);

    Assignee delete(String id);

    Assignee update(Assignee assignee);

    Assignee findById(String id);

    List<Assignee> findAll();

    List<Assignee> merge(List<Assignee> assignees);

    List<Assignee> findByUserId(String userId);

    void setConn(Connection conn);

}
