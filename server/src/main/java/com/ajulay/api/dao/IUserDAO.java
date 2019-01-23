package com.ajulay.api.dao;

import com.ajulay.entity.User;

import java.sql.Connection;
import java.util.List;

/**
 * IAssignerDAO defines base data access methods for Assigner
 */
public interface IUserDAO {

    User create(String login);

    User delete(String id);

    User update(User assigner);

    User findById(String id);

    List<User> findAll();

    List<User> merge(List<User> assigners);

    User findByLogin(String login);

    User save(User user);

    void setConn(Connection conn);

}
