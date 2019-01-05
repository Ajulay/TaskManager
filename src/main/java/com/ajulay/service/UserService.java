package com.ajulay.service;

import com.ajulay.api.dao.IUserDAO;
import com.ajulay.api.service.IUserService;
import com.ajulay.dao.UserDAO;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.unchecked.NullDataForAssignerException;
import com.ajulay.exception.unchecked.NullIdException;

import java.util.List;

/**
 * {@inheritDoc}
 */
public class UserService implements IUserService {

    private final IUserDAO assignerDao = new UserDAO();

    public User createUser(final String surname) {
        if (surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        return assignerDao.create(surname);
    }

    public User deleteUser(final String surname) throws NoSuchAssignerException {
        if (surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        return assignerDao.delete(surname);
    }

    public User updateUser(final User assigner) throws NoSuchAssignerException {
        if (assigner == null) throw new NullPointerException();
        final User oldAssigner = findById(assigner.getId());
        oldAssigner.setName(assigner.getName());
        oldAssigner.setLastName(assigner.getLastName());
        oldAssigner.setSurname(assigner.getSurname());
        return assignerDao.update(oldAssigner);
    }

    public User getBySurname(final String surname) throws NoSuchAssignerException {
        if (surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        for (User assigner : getUsers()) {
            if (surname.equals(assigner.getSurname())) {
                return assigner;
            }
        }
        throw new NoSuchAssignerException();
    }

    public User findById(String id) throws NoSuchAssignerException {
        if (id.isEmpty()) {
            throw new NullIdException();
        }
        return assignerDao.findById(id);
    }

    public List<User> getUsers() {
        return assignerDao.findAll();
    }

    @Override
    public boolean merge(List<User> assigners) {
        return assignerDao.merge(assigners);
    }

    @Override
    public User findByLogin(String login) throws NoSuchAssignerException {
        return assignerDao.findByLogin(login);
    }

    @Override
    public Boolean isLoginExists(String in) {
        try {
            assignerDao.findByLogin(in);
        } catch (NoSuchAssignerException e) {
            return false;
        }
        return true;
    }

}
