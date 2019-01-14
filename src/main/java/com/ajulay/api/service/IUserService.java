package com.ajulay.api.service;


import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IUserService {

    User createUser(String login);

    User deleteUser(String surname) throws NoSuchAssignerException;

    User updateUser(User executor) throws NoSuchAssignerException;

    User getBySurname(String surname);

    User findById(String id);

    List<User> getUsers();

    boolean merge(List<User> assigners);

    User findByLogin(String login) throws NoSuchAssignerException;

    User changePassword(User user, String password);

    User getCurrentUser();

    void setCurrentUser(User currentUser);

}
