package com.ajulay.api.service;


import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IAssignerService {

    User createAssigner(String surname);

    User deleteAssigner(String surname) throws NoSuchAssignerException;

    User updateAssigner(User executor) throws NoSuchAssignerException;

    User getBySurname(String surname) throws NoSuchAssignerException;

    User findById(String id) throws NoSuchAssignerException;

    List<User> getAssigners();

    boolean merge(List<User> assigners);

    User findByLogin(String login) throws NoSuchAssignerException;

    Boolean isLoginExists(String in);

}
