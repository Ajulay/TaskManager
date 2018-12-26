package com.ajulay.service;

import com.ajulay.api.dao.IAssignerDAO;
import com.ajulay.api.service.IAssignerService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.dao.AssignerDAO;
import com.ajulay.entity.Assigner;
import com.ajulay.exception.NoAssignerException;
import com.ajulay.exception.NoIdException;
import com.ajulay.exception.NoSuchAssignerException;

import java.util.List;

/**
 * @inherite
 */
public class AssignerService implements IAssignerService {

    private final IAssignerDAO assignerDao = new AssignerDAO();

    public Assigner createAssigner(final String surname) {
        if (surname == null || ServiceConstant.EMPTY_VALUE.equals(surname)) throw new NoAssignerException();
        return assignerDao.create(surname);
    }

    public Assigner deleteAssigner(final String surname) throws NoSuchAssignerException {
        if (surname == null || ServiceConstant.EMPTY_VALUE.equals(surname)) throw new NoAssignerException();
        return assignerDao.delete(surname);
    }

    public Assigner updateAssigner(final Assigner assigner) throws NoSuchAssignerException {
        if (assigner == null) throw new NullPointerException();
        return assignerDao.delete(assigner.getSurname());
    }

    public Assigner getBySurname(final String surname) throws NoSuchAssignerException {
        if (surname == null || ServiceConstant.EMPTY_VALUE.equals(surname)) throw new NoAssignerException();
        for (Assigner assigner : getAssigners()) {
            if (assigner.getSurname().equals(surname)) {
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    public Assigner findById(String id) throws NoSuchAssignerException {
        if (id == null || ServiceConstant.EMPTY_VALUE.equals(id)) throw new NoIdException();
        return assignerDao.findById(id);
    }

    public List<Assigner> getAssigners() {
        return assignerDao.findAll();
    }

}
