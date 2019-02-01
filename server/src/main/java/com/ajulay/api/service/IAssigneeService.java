package com.ajulay.api.service;

import com.ajulay.entity.Assignee;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * IAssigneeService creates conditions for CRUD operations for Assignee
 */
public interface IAssigneeService {

    Assignee createAssignee(@Nullable String taskId, String assignerId);

    Assignee removeById(String id);

    Assignee update(Assignee assignee);

    Assignee findById(String id);

    List<Assignee> findAll();

    List<Assignee> findAllByUserId(String currentUser);

    List<Assignee> findAllByTaskId(String taskId);

    List<Assignee> removeAllByTaskId(String taskId);

    Assignee save(Assignee assignee);

    Assignee remove(Assignee assignee);

    List<Assignee> updateAll(List<Assignee> assignees);
}
