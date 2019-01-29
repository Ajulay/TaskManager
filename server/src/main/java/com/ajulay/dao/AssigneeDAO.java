package com.ajulay.dao;

import com.ajulay.api.dao.IAssigneeDAO;
import com.ajulay.entity.Assignee;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class AssigneeDAO implements IAssigneeDAO {

    private final List<Assignee> assignees = new ArrayList<>();

    private Connection conn;

    private Assignee fetch(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final Assignee assignee = new Assignee();
        assignee.setId(resultSet.getString("id"));
        assignee.setUserId(resultSet.getString("user_id"));
        assignee.setTaskId(resultSet.getString("task_id"));
        return assignee;
    }

    private List<Assignee> fetchAll(final ResultSet resultSet) throws SQLException {
        final List<Assignee> assignees = new ArrayList<>();
        while (resultSet.next()) {
            final Assignee assignee = new Assignee();
            assignee.setId(resultSet.getString("id"));
            assignee.setUserId(resultSet.getString("user_id"));
            assignee.setTaskId(resultSet.getString("task_id"));
            assignees.add(assignee);
        }
        return assignees;
    }

    @Override
    public Assignee create(final Assignee assignee) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "INSERT INTO assignee(id, user_id, task_id) " +
                            "VALUES (?, ?, ?)");
            statement.setString(1, assignee.getId());
            statement.setString(2, assignee.getUserId());
            statement.setString(3, assignee.getTaskId());
            statement.execute();
            statement.close();
            return assignee;
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
    public Assignee delete(final String id) {
        final Assignee assignee = findById(id);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM assignee WHERE id = '" + id + "'");
            statement.execute();
            statement.close();
            return assignee;
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
    public Assignee update(final Assignee assignee) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "UPDATE assignee SET user_id = ?, task_id = ? WHERE id = ?");
            statement.setString(1, assignee.getUserId());
            statement.setString(2, assignee.getTaskId());
            statement.setString(3, assignee.getId());
            statement.execute();
            statement.close();
            return assignee;
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
    public Assignee findById(final String id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM assignee WHERE id = '" + id + "'");
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
    public List<Assignee> findAll() {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM assignee");
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
    public List<Assignee> merge(final List<Assignee> assignees) {
        for (final Assignee assignee : assignees) {
            if (findById(assignee.getId()) == null) {
                create(assignee);
                continue;
            }
            update(assignee);
        }
        return assignees;
    }

    @Override
    public List<Assignee> findByUserId(final String userId) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM assignee WHERE user_id = '" + userId + "'");
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
    public void setConn(final Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Assignee> findByTaskId(final String taskId) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM assignee WHERE task_id = '" + taskId + "'");
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


}
