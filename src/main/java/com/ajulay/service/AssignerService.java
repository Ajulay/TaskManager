package com.ajulay.service;

import com.ajulay.api.dao.IAssignerDAO;
import com.ajulay.api.service.IAssignerService;
import com.ajulay.dao.AssignerDAO;
import com.ajulay.entity.Assigner;

import java.util.List;

public class AssignerService implements IAssignerService {

    private final IAssignerDAO assignerDao = new AssignerDAO();

    public Assigner createAssigner(final String surname) throws Exception {
        return assignerDao.create(surname);
    }

    public Assigner deleteAssigner(final String surname) throws Exception {
        return assignerDao.delete(surname);
    }

    public Assigner updateAssigner(final Assigner assigner) throws Exception {
        return assignerDao.delete(assigner.getSurname());
    }

    public Assigner getBySurname(final String surname) throws Exception {
        return assignerDao.findById(surname);
    }

    public List<Assigner> getAssigners() {
        return assignerDao.findAll();
    }

}
