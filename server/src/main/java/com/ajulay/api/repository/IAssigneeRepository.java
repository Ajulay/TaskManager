package com.ajulay.api.repository;

import com.ajulay.entity.Assignee;

import java.util.List;

/**
 * IAssigneeDAO defines base data access methods for Assignee
 */
public interface IAssigneeRepository extends IRepository<Assignee> {

    List<Assignee> findByUserId(String userId);

    List<Assignee> findByTaskId(String taskId);

}
