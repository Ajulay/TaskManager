package com.ajulay.api.service;

import com.ajulay.entity.Assignee;
import com.ajulay.exception.checked.NoSuchAssigneeException;

import java.util.List;

/**
 * IAssigneeService creates conditions for CRUD operations for Assignee
 */
public interface IAssigneeService {

    Assignee createAssignee(String taskId, String assignerId);

    Assignee deleteAssignee(String id) throws NoSuchAssigneeException;

    Assignee updateAssignee(Assignee assignee) throws NoSuchAssigneeException;

    Assignee getById(String id) throws NoSuchAssigneeException;

    List<Assignee> findAllAssignee();

}
