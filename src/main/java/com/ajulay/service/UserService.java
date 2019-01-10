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

    private User currentUser;

    private final IUserDAO userDao = new UserDAO();

    public User createUser(final String surname) {
        if (surname == null || surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        return userDao.create(surname);
    }

    public User deleteUser(final String surname) throws NoSuchAssignerException {
        if (surname == null || surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        return userDao.delete(surname);
    }

    public User updateUser(final User assigner) throws NoSuchAssignerException {
        if (assigner == null) throw new NullPointerException();
        final User oldAssigner = findById(assigner.getId());
        oldAssigner.setName(assigner.getName());
        oldAssigner.setLastName(assigner.getLastName());
        oldAssigner.setSurname(assigner.getSurname());
        return userDao.update(oldAssigner);
    }

    public User getBySurname(final String surname) throws NoSuchAssignerException {
        if (surname == null || surname.isEmpty()) {
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
        if (id == null || id.isEmpty()) {
            throw new NullIdException();
        }
        return userDao.findById(id);
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public boolean merge(final List<User> users) {
        if (users == null) return true;
        return userDao.merge(users);
    }

    @Override
    public User findByLogin(final String login) throws NoSuchAssignerException {
        return userDao.findByLogin(login);
    }

    @Override
    public Boolean isLoginExists(final String in) {
        try {
            userDao.findByLogin(in);
        } catch (NoSuchAssignerException e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean changePassword(String password) {

        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
