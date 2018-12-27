package com.ajulay.api.service;


import com.ajulay.entity.Assigner;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IAssignerService {

    Assigner createAssigner(String surname);

    Assigner deleteAssigner(String surname) throws NoSuchAssignerException;

    Assigner updateAssigner(Assigner executor) throws NoSuchAssignerException;

    Assigner getBySurname(String surname) throws NoSuchAssignerException;

    Assigner findById(String id) throws NoSuchAssignerException;

    List<Assigner> getAssigners();

}
