package com.ajulay.api.dao;

import com.ajulay.entity.Assigner;


import java.util.List;

public interface IAssignerDAO2 {

    Assigner create(String surname) throws Exception;

    Assigner delete(String surname) throws Exception;

    Assigner update(Assigner assigner) throws Exception;

    Assigner findById(String surname) throws Exception;

    List<Assigner> findAll();
}
