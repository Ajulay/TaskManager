package com.ajulay.service;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IAssignerService;
import com.ajulay.api.service.ITaskService;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.Assigner;
import com.ajulay.entity.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class AssigneeServiceTest {

    @Test
    public void createAssignee() throws Exception {
        final IAssigneeService service = new AssigneeService();
        final Assignee assignee = service.createAssignee(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        Assert.assertNotNull(assignee);
    }

    @Test(expected = Exception.class)
    public void deleteAssignee() throws Exception {
        final AssigneeService service = new AssigneeService();
        service.deleteAssignee(UUID.randomUUID().toString());
    }

    @Test
    public void updateAssignee() throws Exception {
        final IAssigneeService service = new AssigneeService();
        final ITaskService taskService = new TaskService();
        final IAssignerService assignerService = new AssignerService();
        final Task task = taskService.saveTask(new Task());
        final String taskId = task.getId();
        final Assigner assigner = assignerService.createAssigner("Petrov");
        final String assignerId = assigner.getId();
        final Assignee assignee = service.createAssignee(taskId, assignerId);
        final Assigner assigner2 = assignerService.createAssigner("Ivanov");
        final String assigner2Id = assigner2.getId();
        final String assigneeId = assignee.getId();
        final Assignee assignee2 = service.getById(assigneeId);
        assignee2.setAssignerId(assigner2Id);

        Assert.assertEquals(assignee.getAssignerId(), assignee2.getAssignerId());

    }

    @Test
    public void getById() throws Exception {
        final IAssigneeService service = new AssigneeService();
        final ITaskService taskService = new TaskService();
        final IAssignerService assignerService = new AssignerService();
        final Task task = taskService.saveTask(new Task());
        final String taskId = task.getId();
        final Assigner assigner = assignerService.createAssigner("Ivlev");
        final String assignerId = assigner.getId();
        final Assignee assignee = service.createAssignee(taskId, assignerId);
        final String assigneeId = assignee.getId();
        final Assignee assignee2 = service.getById(assigneeId);

        Assert.assertNotNull(assignee2);

    }

    @Test
    public void assigneeFindAll() throws Exception {
        final IAssigneeService service = new AssigneeService();
        final ITaskService taskService = new TaskService();
        final IAssignerService assignerService = new AssignerService();
        for (int i = 1; i <= 10; i++) {
            final Task task = taskService.saveTask(new Task());
            final String taskId = task.getId();
            final Assigner assigner = assignerService.createAssigner("Ivlev" + i);
            final String assignerId = assigner.getId();
            service.createAssignee(taskId, assignerId);
        }

        Assert.assertEquals(service.AssigneeFindAll().size(), 10);
    }

}