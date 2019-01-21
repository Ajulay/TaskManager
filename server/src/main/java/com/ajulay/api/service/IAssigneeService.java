package com.ajulay.api.service;

import com.ajulay.entity.Assignee;

import java.util.List;

/**
 * IAssigneeService creates conditions for CRUD operations for Assignee
 */
public interface IAssigneeService {

    Assignee createAssignee(String taskId, String assignerId);

    Assignee deleteAssignee(String id);

    Assignee updateAssignee(Assignee assignee);

    Assignee getById(String id);

    List<Assignee> findAllAssignee();

    List<Assignee> merge(List<Assignee> assignees);

    List<Assignee> findAssigneeAllByUserId(String currentUser);

}
