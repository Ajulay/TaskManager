package com.ajulay.service;

import com.ajulay.api.service.IUserService;
import com.ajulay.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AssignerServiceTest {

    @Test
    public void createAssigner() throws Exception {
        final IUserService service = new UserService();
        final String surname = "Ivlevich";
        final User assigner = service.createUser(surname);

        Assert.assertTrue(assigner != null && assigner.getSurname().equals(surname));
    }

    @Test
    public void deleteAssigner() throws Exception {
        final IUserService service = new UserService();
        final String surname = "Ivlevich";
        final User assigner = service.createUser(surname);
        final User deletedAssigner = service.deleteUser(surname);

        Assert.assertEquals(assigner, deletedAssigner);
    }

    @Test(expected = Exception.class)
    public void updateAssigner() throws Exception {
        final IUserService service = new UserService();
        service.findById("5");
    }

    @Test
    public void findById() throws Exception {
        final IUserService service = new UserService();
        final String surname = "Ivlevich";
        final User assigner = service.createUser(surname);
        final User findedAssigner = service.findById(assigner.getId());

        Assert.assertEquals(assigner, findedAssigner);
    }

    @Test
    public void getAssigners() throws Exception {
        final IUserService service = new UserService();
        final int controlNumber = 5;
        for (int i = 1; i <= controlNumber; i++) {
            final String surname = "Ivlevich" + i;
            service.createUser(surname);
        }

        Assert.assertEquals(service.getUsers().size(), controlNumber);
    }

    @Test
    public void merge() {
        final IUserService service = new UserService();
        service.createUser("Petrov44");
        final List<User> assigners = Arrays.asList(new User(), new User(), new User());
        service.merge(assigners);

        Assert.assertEquals(service.getUsers().size(), 3);
    }

}