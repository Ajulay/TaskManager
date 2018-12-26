package com.ajulay.service;

import com.ajulay.api.service.ITaskService;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;
import org.junit.Assert;
import org.junit.Test;

public class TaskServiceTest {

    @Test
    public void saveTask() {
        final ITaskService service = new TaskService();
        final Task task = service.saveTask(new Task());

        Assert.assertNotNull(task);
    }

    @Test
    public void deleteTask() throws Exception {
        final ITaskService service = new TaskService();
        final Task task = service.saveTask(new Task());
        final Task deletedTask = service.deleteTask(task.getId());

        Assert.assertEquals(task, deletedTask);
    }

    @Test
    public void changeStatus() throws Exception {
        final ITaskService service = new TaskService();
        final Task task = service.saveTask(new Task());
        final Task task2 = service.findTaskById(task.getId());
        task2.setStatus(Status.FAILED);

        Assert.assertEquals(task.getStatus(), Status.FAILED);
    }

    @Test
    public void findTaskAll() {
        final ITaskService service = new TaskService();
        final int controlNumber = 5;
        for (int i = 1; i <= controlNumber; i++) {
            service.saveTask(new Task());
        }

        Assert.assertEquals(service.findTaskAll().size(), controlNumber);
    }

    @Test
    public void findTaskAllByProject() {
        final ITaskService service = new TaskService();
        final int controlNumber = 5;
        final Project project = new Project();
        for (int i = 1; i <= controlNumber; i++) {
            final Task task = new Task();
            if (i % 2 == 0) {
                task.setProjectId(project.getId());
            } else {
                task.setProjectId("123");
            }
            service.saveTask(task);
        }

        Assert.assertEquals(service.findTaskAllByProject(project.getId()).size(), controlNumber / 2);
    }

}