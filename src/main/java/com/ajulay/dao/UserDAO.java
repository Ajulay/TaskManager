package com.ajulay.dao;

import com.ajulay.api.dao.IUserDAO;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class UserDAO implements IUserDAO {

    private final List<User> users = new ArrayList<>();

    @Override
    public User create(final String surname) {
        final User user = new User();
        user.setSurname(surname);
        users.add(user);
        return user;
    }

    @Override
    public User delete(final String surname) throws NoSuchAssignerException {
        for (User user : users) {
            if (surname.equals(user.getSurname())) {
                users.remove(user);
                return user;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public User update(final User user) throws NoSuchAssignerException {
        for (final User tmpUser : users) {
            if (tmpUser.getId().equals(user.getId())) {
                users.remove(tmpUser);
                users.add(user);
                return user;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public User findById(final String id) throws NoSuchAssignerException {
        for (final User user : users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        throw new NoSuchAssignerException();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean merge(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        return true;
    }

    @Override
    public User findByLogin(String login) throws NoSuchAssignerException {
        for (final User usner : users) {
            if (login.equals(usner.getLogin())) {
                return usner;
            }
        }
        throw new NoSuchAssignerException();
    }

}
