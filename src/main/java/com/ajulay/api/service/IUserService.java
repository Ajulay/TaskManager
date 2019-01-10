package com.ajulay.api.service;


import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IUserService {

    User createUser(String surname);

    User deleteUser(String surname) throws NoSuchAssignerException;

    User updateUser(User executor) throws NoSuchAssignerException;

    User getBySurname(String surname) throws NoSuchAssignerException;

    User findById(String id) throws NoSuchAssignerException;

    List<User> getUsers();

    boolean merge(List<User> assigners);

    User findByLogin(String login) throws NoSuchAssignerException;

    Boolean isLoginExists(String in);

    Boolean changePassword(String password);

    User getCurrentUser();

    void setCurrentUser(User currentUser);

}
