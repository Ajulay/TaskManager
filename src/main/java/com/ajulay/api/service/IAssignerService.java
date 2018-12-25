package com.ajulay.api.service;


import com.ajulay.entity.Assigner;

import java.util.List;

public interface IAssignerService {

    Assigner createAssigner(String surname) throws Exception;

    Assigner deleteAssigner(String surname) throws Exception;

    Assigner updateAssigner(Assigner executor) throws Exception;

    Assigner getBySurname(String surname) throws Exception;

    List<Assigner> getAssigners();

}
