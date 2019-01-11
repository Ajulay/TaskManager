package com.ajulay.command;

import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.service.UserService;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationCommandTest {

    @Test(expected = NoSuchAssignerException.class)
    public void executeNegative() throws NoSuchAssignerException {
        final UserService userService = new UserService();
        final User user = new User();
        final String login = "test";
        user.setLogin(login);
        userService.findById(user.getId());
    }

    @Test
    public void executePositive() throws NoSuchAssignerException {
        final UserService userService = new UserService();
        final User user = new User();
        final String login = "test";
        user.setLogin(login);
        userService.updateUser(user);

        Assert.assertEquals(userService.findById(user.getId()), user);
    }

}