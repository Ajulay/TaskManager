package com.ajulay.api.service;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Assigner;

import java.util.List;

public interface IAssigneeService {

    Assignee createAssignee(String taskId, String assignerId) throws Exception;

    Assignee deleteAssignee(String id) throws Exception;

    Assignee updateAssignee(Assignee assignee) throws Exception;

    Assignee getById(String id) throws Exception;

    List<Assignee> AssigneeFindAll();

}
