package com.ajulay.dao;

import com.ajulay.api.dao.IAssignerDAO;
import com.ajulay.entity.Assigner;
import com.ajulay.exception.NoSuchAssignerException;

import java.util.ArrayList;
import java.util.List;

/**
 * @inherite
 */
public class AssignerDAO implements IAssignerDAO {

    private final List<Assigner> assigners = new ArrayList<>();

    @Override
    public Assigner create(final String surname) {
        final Assigner assigner = new Assigner(surname);
        findAll().add(assigner);
        return assigner;
    }

    @Override
    public Assigner delete(final String surname) throws NoSuchAssignerException {
        for (Assigner assigner : findAll()) {
            if (assigner.getSurname().equals(surname)) {
                findAll().remove(assigner);
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public Assigner update(final Assigner assigner) throws NoSuchAssignerException {
        final Assigner oldAssigner = findById(assigner.getId());
        oldAssigner.setName(assigner.getName());
        oldAssigner.setLastName(assigner.getLastName());
        oldAssigner.setSurname(assigner.getSurname());
        return oldAssigner;
    }

    @Override
    public Assigner findById(final String id) throws NoSuchAssignerException {
        for (Assigner assigner : findAll()) {
            if (assigner.getId().equals(id)) {
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public List<Assigner> findAll() {
        return assigners;
    }
}
