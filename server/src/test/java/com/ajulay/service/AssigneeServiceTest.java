package com.ajulay.service;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.entity.Assignee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"classpath:frontend-beans.xml"})
public class AssigneeServiceTest {

    private IAssigneeService assigneeService;

    @Test
    public void createAssignee() {
        final String taskId = "task11";
        final String userId = "task11";
        final Assignee assignee = assigneeService.createAssignee(taskId, userId);
        Assert.assertEquals(assignee.getTaskId(), "task11");
    }

    @Test
    public void removeById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllByUserId() {
    }

    @Test
    public void findAllByTaskId() {
    }

    @Test
    public void removeAllByTaskId() {
    }

    @Test
    public void save() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void updateAll() {
    }

}