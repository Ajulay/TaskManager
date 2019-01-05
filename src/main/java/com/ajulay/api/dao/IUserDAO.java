package com.ajulay.api.dao;

import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.List;

/**
 * IAssignerDAO defines base data access methods for Assigner
 */
public interface IUserDAO {

    User create(String surname);

    User delete(String surname) throws NoSuchAssignerException;

    User update(User assigner) throws NoSuchAssignerException;

    User findById(String surname) throws NoSuchAssignerException;

    List<User> findAll();

    boolean merge(List<User> assigners);

    User findByLogin(String login) throws NoSuchAssignerException;

}
