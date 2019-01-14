package com.ajulay.service;

import com.ajulay.api.dao.IUserDAO;
import com.ajulay.api.service.IUserService;
import com.ajulay.dao.UserDAO;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.unchecked.LoginExistsException;
import com.ajulay.exception.unchecked.NullDataForAssignerException;
import com.ajulay.exception.unchecked.NullIdException;

import java.util.List;

/**
 * {@inheritDoc}
 */
public class UserService implements IUserService {

    private User currentUser;

    private final IUserDAO userDao = new UserDAO();

    public User createUser(final String login) {
        if (login == null || login.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        if (isLoginExists(login)) {
            throw new LoginExistsException();
        }
        final User user = new User();
        user.setLogin(login);
        return userDao.save(user);
    }

    public User deleteUser(final String surname) throws NoSuchAssignerException {
        if (surname == null || surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        return userDao.delete(surname);
    }

    public User updateUser(final User user) throws NoSuchAssignerException {
        if (user == null) throw new NullPointerException();
        final User oldAssigner = findById(user.getId());
        if (oldAssigner == null) return userDao.save(user);
        oldAssigner.setName(user.getName());
        oldAssigner.setLastName(user.getLastName());
        oldAssigner.setSurname(user.getSurname());
        return userDao.update(oldAssigner);
    }

    public User getBySurname(final String surname) {
        if (surname == null || surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        for (User assigner : getUsers()) {
            if (surname.equals(assigner.getSurname())) {
                return assigner;
            }
        }
        return null;
    }

    public User findById(String id) {
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

    private Boolean isLoginExists(final String in) {
        try {
            userDao.findByLogin(in);
        } catch (NoSuchAssignerException e) {
            return false;
        }
        return true;
    }

    @Override
    public User changePassword(User user, String password) {
        user.setPassword(password);
        return user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
