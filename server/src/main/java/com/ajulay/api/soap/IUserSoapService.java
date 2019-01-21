package com.ajulay.api.soap;

import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;

import java.util.List;

public interface IUserSoapService {

    Success deleteUser(Session session, String surname);

    Success updateUser(Session session, User executor);

    User getBySurname(Session session, String surname);

    User findById(Session session, String id);

    List<User> getUsers(Session session);

    Success merge(Session session, List<User> users);

    User findByLogin(Session session, String login);

    Success changePassword(Session session, User user, String password);

    List<User> findUserAllByTaskId(Session session, String taskId);

}
