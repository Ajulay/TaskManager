package com.ajulay.api.service;


import com.ajulay.api.dao.IUserDAO;
import com.ajulay.entity.User;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IUserService {

    User createUser(String login) throws Exception;

    User deleteUser(String id);

    User updateUser(User user) throws Exception;

    User getBySurname(String surname);

    User findById(String id);

    List<User> getUsers();

    List<User> merge(List<User> assigners);

    User findByLogin(String login);

    User changePassword(User user, String password);

    User getCurrentUser();

    void setCurrentUser(User currentUser);

    IUserDAO getUserDao();

}
