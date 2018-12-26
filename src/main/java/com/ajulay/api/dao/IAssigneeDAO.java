package com.ajulay.api.dao;

import com.ajulay.entity.Assignee;
import com.ajulay.exception.NoSuchAssigneeException;

import java.util.List;

/**
 * IAssigneeDAO defines base data access methods for Assignee
 */
public interface IAssigneeDAO {

    Assignee create(Assignee assignee);

    Assignee delete(String id) throws NoSuchAssigneeException;

    Assignee update(Assignee assignee) throws NoSuchAssigneeException;

    Assignee findById(String id) throws NoSuchAssigneeException;

    List<Assignee> findAll();
}
