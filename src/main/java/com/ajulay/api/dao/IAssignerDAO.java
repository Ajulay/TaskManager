package com.ajulay.api.dao;

import com.ajulay.entity.Assigner;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.List;

/**
 * IAssignerDAO defines base data access methods for Assigner
 */
public interface IAssignerDAO {

    Assigner create(String surname);

    Assigner delete(String surname) throws NoSuchAssignerException;

    Assigner update(Assigner assigner) throws NoSuchAssignerException;

    Assigner findById(String surname) throws NoSuchAssignerException;

    List<Assigner> findAll();
}
