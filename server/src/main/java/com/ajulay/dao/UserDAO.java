package com.ajulay.dao;

import com.ajulay.api.dao.IUserDAO;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class UserDAO implements IUserDAO {

    private Connection conn;

    private User fetch(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final User user = new User();
        user.setId(resultSet.getString("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password_hash"));
        user.setSurname(resultSet.getString("surname"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("lastname"));
        final String role = resultSet.getString("role");
        if (role != null) user.setRole(Role.valueOf(role));
        return user;
    }

    private List<User> fetchAll(final ResultSet resultSet) throws SQLException {
        final List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            final User user = new User();
            user.setId(resultSet.getString("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password_hash"));
            user.setSurname(resultSet.getString("surname"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastname"));
            user.setRole(Role.valueOf(resultSet.getString("role")));
            users.add(user);
        }
        return users;
    }

    @Override
    public User create(final String login) {
        final User user = new User();
        user.setLogin(login);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "INSERT INTO \"user\"(id, login, role) VALUES (?, ?, ?)");
            statement.setString(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getRole().name());
            statement.execute();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User delete(final String id) {
        final User user = findById(id);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM \"user\" WHERE id = '" + id + "'");
            statement.execute();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User update(final User user) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "UPDATE \"user\" SET login = ?, password_hash = ?, surname = ?, role = ? WHERE id = ?");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getId());
            statement.execute();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User findById(final String id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM \"user\" WHERE id = '" + id + "'");
            final ResultSet result = statement.executeQuery();
            return fetch(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM \"user\"");
            final ResultSet result = statement.executeQuery();
            return fetchAll(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<User> merge(final List<User> users) {
        for (final User user : users) {
            if (findById(user.getId()) == null) {
                save(user);
                continue;
            }
            update(user);
        }
        return users;
    }

    @Override
    public User findByLogin(final String login) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM \"user\" WHERE login = '" + login + "'");
            final ResultSet result = statement.executeQuery();
            return fetch(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public User save(final User user) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "INSERT INTO \"user\"(id, login, password_hash, name, surname, lastname, role) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getRole().toString());
            statement.execute();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void setConn(final Connection conn) {
        this.conn = conn;
    }

}
