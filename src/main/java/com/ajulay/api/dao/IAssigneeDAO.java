package com.ajulay.api.dao;

import com.ajulay.entity.Assignee;

import java.util.List;

public interface IAssigneeDAO {

    Assignee create(Assignee assignee);

    Assignee delete(String id) throws Exception;

    Assignee update(Assignee assignee) throws Exception;

    Assignee findById(String id) throws Exception;

    List<Assignee> findAll();
}
