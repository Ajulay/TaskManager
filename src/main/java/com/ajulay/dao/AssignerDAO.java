package com.ajulay.dao;

import com.ajulay.api.dao.IAssignerDAO;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class AssignerDAO implements IAssignerDAO {

    private final List<User> assigners = new ArrayList<>();

    @Override
    public User create(final String surname) {
        final User assigner = new User();
        assigner.setSurname(surname);
        assigners.add(assigner);
        return assigner;
    }

    @Override
    public User delete(final String surname) throws NoSuchAssignerException {
        for (User assigner : assigners) {
            if (surname.equals(assigner.getSurname())) {
                assigners.remove(assigner);
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public User update(final User assigner) throws NoSuchAssignerException {
        for (final User asser : assigners) {
            if (asser.getId().equals(assigner.getId())) {
                assigners.remove(asser);
                assigners.add(assigner);
                return asser;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public User findById(final String id) throws NoSuchAssignerException {
        for (final User assigner : assigners) {
            if (id.equals(assigner.getId())) {
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public List<User> findAll() {
        return assigners;
    }

    @Override
    public boolean merge(List<User> assigners) {
        this.assigners.clear();
        this.assigners.addAll(assigners);
        return true;
    }

    @Override
    public User findByLogin(String login) throws NoSuchAssignerException {
        for (final User assigner : assigners) {
            if (login.equals(assigner.getLogin())) {
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

}
