package com.ajulay.api.service;


import com.ajulay.entity.Assigner;

import java.util.List;

/**
 * IAssignerService creates conditions for CRUD operations for Assigner
 */
public interface IAssignerService {

    Assigner createAssigner(String surname) throws Exception;

    Assigner deleteAssigner(String surname) throws Exception;

    Assigner updateAssigner(Assigner executor) throws Exception;

    Assigner getBySurname(String surname) throws Exception;

    Assigner findById(String id) throws Exception;

    List<Assigner> getAssigners();

}
