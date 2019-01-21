package com.ajulay.api.service;


import com.ajulay.entity.User;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IUserService {

    User createUser(String login);

    User deleteUser(String surname);

    User updateUser(User user);

    User getBySurname(String surname);

    User findById(String id);

    List<User> getUsers();

    List<User> merge(List<User> assigners);

    User findByLogin(String login);

    User changePassword(User user, String password);

    User getCurrentUser();

    void setCurrentUser(User currentUser);

}
