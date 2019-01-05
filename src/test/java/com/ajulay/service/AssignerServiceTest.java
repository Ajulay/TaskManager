package com.ajulay.service;

import com.ajulay.api.service.IAssignerService;
import com.ajulay.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AssignerServiceTest {

    @Test
    public void createAssigner() throws Exception {
        final IAssignerService service = new AssignerService();
        final String surname = "Ivlevich";
        final User assigner = service.createAssigner(surname);

        Assert.assertTrue(assigner != null && assigner.getSurname().equals(surname));
    }

    @Test
    public void deleteAssigner() throws Exception {
        final IAssignerService service = new AssignerService();
        final String surname = "Ivlevich";
        final User assigner = service.createAssigner(surname);
        final User deletedAssigner = service.deleteAssigner(surname);

        Assert.assertEquals(assigner, deletedAssigner);
    }

    @Test(expected = Exception.class)
    public void updateAssigner() throws Exception {
        final IAssignerService service = new AssignerService();
        service.findById("5");
    }

    @Test
    public void findById() throws Exception {
        final IAssignerService service = new AssignerService();
        final String surname = "Ivlevich";
        final User assigner = service.createAssigner(surname);
        final User findedAssigner = service.findById(assigner.getId());

        Assert.assertEquals(assigner, findedAssigner);
    }

    @Test
    public void getAssigners() throws Exception {
        final IAssignerService service = new AssignerService();
        final int controlNumber = 5;
        for (int i = 1; i <= controlNumber; i++) {
            final String surname = "Ivlevich" + i;
            service.createAssigner(surname);
        }

        Assert.assertEquals(service.getAssigners().size(), controlNumber);
    }

    @Test
    public void merge() {
        final IAssignerService service = new AssignerService();
        service.createAssigner("Petrov44");
        final List<User> assigners = Arrays.asList(new User(), new User(), new User());
        service.merge(assigners);

        Assert.assertEquals(service.getAssigners().size(), 3);
    }

}