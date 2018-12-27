package com.ajulay.dao;

import com.ajulay.api.dao.IAssignerDAO;
import com.ajulay.entity.Assigner;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
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
            if (surname.equals(assigner.getSurname())) {
                findAll().remove(assigner);
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public Assigner update(final Assigner assigner) throws NoSuchAssignerException {
        for (final Assigner asser : assigners) {
            if (asser.getId().equals(assigner.getId())) {
                assigners.remove(asser);
                assigners.add(assigner);
                return asser;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public Assigner findById(final String id) throws NoSuchAssignerException {
        for (final Assigner assigner : findAll()) {
            if (id.equals(assigner.getId())) {
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
