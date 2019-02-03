package com.ajulay.api.service;


import com.ajulay.entity.User;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IUserService {

    User createByLogin(String login);

    User removeById(String id);

    User update(User user);

    User findById(String id);

    User findByLogin(String login);

    User changePassword(User user, String password);

    User getCurrentUser();

    void setCurrentUser(@Nullable User currentUser);

    User save(User user);

    User remove(User user);

    List<User> findAll();

    List<User> updateAll(List<User> users);

    List<User> findUserAllByTaskId(String taskId);

    User changeSurname(String id, String surname);

}
