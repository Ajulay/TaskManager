package com.ajulay.dao;

import com.ajulay.api.dao.IUserDAO;
import com.ajulay.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class UserDAO implements IUserDAO {

    private final List<User> users = new ArrayList<>();

    @Override
    public User create(final String login) {
        final User user = new User();
        user.setLogin(login);
        users.add(user);
        return user;
    }

    @Override
    public User delete(final String surname) {
        for (User user : users) {
            if (surname.equals(user.getSurname())) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User update(final User user) {
        for (final User tmpUser : users) {
            if (tmpUser.getId().equals(user.getId())) {
                users.remove(tmpUser);
                users.add(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User findById(final String id) {
        for (final User user : users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public List<User> merge(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        return users;
    }

    @Override
    public User findByLogin(String login) {
        for (final User usner : users) {
            if (login.equals(usner.getLogin())) {
                return usner;
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

}
