package com.ajulay.service;

import com.ajulay.api.service.IUserService;
import com.ajulay.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UserServiceTest {

    @Test
    public void createUser() {
        final IUserService service = new UserService();
        final String surname = "Ivlevich";
        final User user = service.createUser(surname);

        Assert.assertTrue(user != null && user.getSurname().equals(surname));
    }

    @Test
    public void deleteUser() throws Exception {
        final IUserService service = new UserService();
        final String surname = "Ivlevich";
        final User user = service.createUser(surname);
        final User deletedUser = service.deleteUser(surname);

        Assert.assertEquals(user, deletedUser);
    }

    @Test(expected = Exception.class)
    public void updateUser() throws Exception {
        final IUserService service = new UserService();
        service.findById("5");
    }

    @Test
    public void findById() throws Exception {
        final IUserService service = new UserService();
        final String surname = "Ivlevich";
        final User user = service.createUser(surname);
        final User findedUser = service.findById(user.getId());

        Assert.assertEquals(user, findedUser);
    }

    @Test
    public void getUsers() throws Exception {
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