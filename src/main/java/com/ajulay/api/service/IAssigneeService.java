package com.ajulay.api.service;

import com.ajulay.entity.Assignee;

import java.util.List;

/**
 * IAssigneeService creates conditions for CRUD operations for Assignee
 */
public interface IAssigneeService {

    Assignee createAssignee(String taskId, String assignerId) throws Exception;

    Assignee deleteAssignee(String id) throws Exception;

    Assignee updateAssignee(Assignee assignee) throws Exception;

    Assignee getById(String id) throws Exception;

    List<Assignee> AssigneeFindAll();

}
