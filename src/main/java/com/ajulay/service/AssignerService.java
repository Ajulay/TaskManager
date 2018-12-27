package com.ajulay.service;

import com.ajulay.api.dao.IAssignerDAO;
import com.ajulay.api.service.IAssignerService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.dao.AssignerDAO;
import com.ajulay.entity.Assigner;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.unchecked.NullDataForAssignerException;
import com.ajulay.exception.unchecked.NullIdException;

import java.util.List;

/**
 * @inherite
 */
public class AssignerService implements IAssignerService {

    private final IAssignerDAO assignerDao = new AssignerDAO();

    public Assigner createAssigner(final String surname) {
        if (surname == null || ServiceConstant.EMPTY_VALUE.equals(surname)) {
            throw new NullDataForAssignerException();
        }
        return assignerDao.create(surname);
    }

    public Assigner deleteAssigner(final String surname) throws NoSuchAssignerException {
        if (surname == null || ServiceConstant.EMPTY_VALUE.equals(surname)) {
            throw new NullDataForAssignerException();
        }
        return assignerDao.delete(surname);
    }

    public Assigner updateAssigner(final Assigner assigner) throws NoSuchAssignerException {
        if (assigner == null) throw new NullPointerException();
        final Assigner oldAssigner = findById(assigner.getId());
        oldAssigner.setName(assigner.getName());
        oldAssigner.setLastName(assigner.getLastName());
        oldAssigner.setSurname(assigner.getSurname());
        return assignerDao.update(oldAssigner);
    }

    public Assigner getBySurname(final String surname) throws NoSuchAssignerException {
        if (surname == null || ServiceConstant.EMPTY_VALUE.equals(surname)) {
            throw new NullDataForAssignerException();
        }
        for (Assigner assigner : getAssigners()) {
            if (assigner.getSurname().equals(surname)) {
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    public Assigner findById(String id) throws NoSuchAssignerException {
        if (id == null || ServiceConstant.EMPTY_VALUE.equals(id)) {
            throw new NullIdException();
        }
        return assignerDao.findById(id);
    }

    public List<Assigner> getAssigners() {
        return assignerDao.findAll();
    }

}
