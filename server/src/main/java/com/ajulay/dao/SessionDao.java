package com.ajulay.dao;

import com.ajulay.api.dao.ISessionDao;
import com.ajulay.entity.Session;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SessionDao implements ISessionDao {

    //  private final List<Session> sessions = new ArrayList<>();

    private Connection conn;

    private Session fetch(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final Session session = new Session();
        session.setId(resultSet.getString("id"));
        session.setUserId(resultSet.getString("user_id"));
        session.setSignature(resultSet.getString("signature"));
        final Instant iterm = resultSet.getTimestamp("created_date").toInstant();
        final Date term = Date.from(iterm);
        session.setCreatedDate(term);
        return session;
    }

    private List<Session> fetchAll(final ResultSet resultSet) throws SQLException {
        final List<Session> sessions = new ArrayList<>();
        while (resultSet.next()) {
            final Session session = new Session();
            session.setId(resultSet.getString("id"));
            session.setUserId(resultSet.getString("user_id"));
            session.setSignature(resultSet.getString("signature"));
            final Instant iterm = resultSet.getTimestamp("created_date").toInstant();
            final Date term = Date.from(iterm);
            session.setCreatedDate(term);
            sessions.add(session);
        }
        return sessions;
    }
    @Override
    public Session save(final Session session) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "INSERT INTO session(id, user_id, signature, created_date) " +
                            "VALUES (?, ?, ?, ?)");
            statement.setString(1, session.getId());
            statement.setString(2, session.getUserId());
            statement.setString(3, session.getSignature());
            final Timestamp iTerm = Timestamp.from(session.getCreatedDate().toInstant());
            statement.setTimestamp(4, iTerm);
            statement.execute();
            statement.close();
            return session;
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
    public Session delete(final Session session) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM task WHERE id = '" + session.getId() + "'");
            statement.execute();
            statement.close();
            return session;
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
    public Session findById(final String id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM session WHERE id = '" + id + "'");
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
    public Session findBySigniture(final String signiture) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM session WHERE signature = '" + signiture + "'");
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
    public List<Session> findAll() {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM session");
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
        return Collections.emptyList();
    }

    @Override
    public List<Session> addSessionAll(final List<Session> sessions) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "INSERT INTO session(id, user_id, signature, created_date) " +
                            "VALUES (?, ?, ?, ?)");
            for (final Session session : sessions) {
                statement.setString(1, session.getId());
                statement.setString(2, session.getUserId());
                statement.setString(3, session.getSignature());
                final Timestamp iTerm = Timestamp.from(session.getCreatedDate().toInstant());
                statement.setTimestamp(4, iTerm);
                statement.addBatch();
            }
            statement.execute();
            statement.close();
            return sessions;
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
    public List<Session> removeAll(final List<Session> sessions) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM session WHERE id = ?");
            for (final Session session : sessions) {
                statement.setString(1, session.getId());
                statement.addBatch();
            }
            statement.execute();
            statement.close();
            return sessions;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<Session> findSessionByUserId(final String userId) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM session WHERE user_id = '" + userId + "'");
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
    public List<Session> deleteAll() {
        final List<Session> sessions = findAll();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM session");
            statement.execute();
            statement.close();
            return sessions;
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
