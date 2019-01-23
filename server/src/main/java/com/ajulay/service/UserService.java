package com.ajulay.service;

import com.ajulay.api.dao.IUserDAO;
import com.ajulay.api.service.IUserService;
import com.ajulay.dao.UserDAO;
import com.ajulay.entity.User;
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
        return userDao.create(login);
    }

    public User deleteUser(final String id) {
        if (id == null || id.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        return userDao.delete(id);
    }

    public User updateUser(final User user) throws Exception {
        if (user == null) throw new NullPointerException();
        final User oldAssigner = findById(user.getId());
        if (oldAssigner == null) return userDao.save(user);
        oldAssigner.setName(user.getName());
        oldAssigner.setLastName(user.getLastName());
        oldAssigner.setSurname(user.getSurname());
        oldAssigner.setPassword(user.getPassword());
        oldAssigner.setRole(user.getRole());
        return userDao.update(oldAssigner);
    }

    public User getBySurname(final String surname) {
        if (surname == null || surname.isEmpty()) {
            throw new NullDataForAssignerException();
        }
        for (final User user : getUsers()) {
            if (surname.equals(user.getSurname())) {
                return user;
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
    public List<User> merge(final List<User> users) {
        if (users == null) return null;
        return userDao.merge(users);
    }

    @Override
    public User findByLogin(final String login) {
        return userDao.findByLogin(login);
    }

    private Boolean isLoginExists(final String in) {
        final User user = userDao.findByLogin(in);
        if (user == null) return false;
        return true;
    }

    @Override
    public User changePassword(User user, String password) {
        user.setPassword(password);
        userDao.update(user);
        return user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public IUserDAO getUserDao() {
        return userDao;
    }

}
