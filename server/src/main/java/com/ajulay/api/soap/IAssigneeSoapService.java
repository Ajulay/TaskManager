package com.ajulay.api.soap;

import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Session;

import java.util.List;

/**
 * IAssigneeService creates conditions for CRUD operations for Assignee
 */
public interface IAssigneeSoapService {

    Success createAssignee(Session session, String taskId, String assignerId);

    Success deleteAssignee(Session session, String id);

    Success updateAssignee(Session session, Assignee assignee);

    Assignee getById(Session session, String id);

    List<Assignee> findAllAssignee(Session session);

    Success merge(Session session, List<Assignee> assignees);

    List<Assignee> findAssigneeAllByUserId(Session session, String userId);

}
