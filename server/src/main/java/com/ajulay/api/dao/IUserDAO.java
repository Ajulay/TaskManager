package com.ajulay.api.dao;

import com.ajulay.entity.User;

import java.util.List;

/**
 * IAssignerDAO defines base data access methods for Assigner
 */
public interface IUserDAO {

    User create(String login);

    User delete(String surname);

    User update(User assigner);

    User findById(String surname);

    List<User> findAll();

    List<User> merge(List<User> assigners);

    User findByLogin(String login);

    User save(User user);

}
