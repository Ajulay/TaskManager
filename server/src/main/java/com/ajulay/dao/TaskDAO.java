package com.ajulay.dao;

import com.ajulay.api.dao.ITaskDAO;
import com.ajulay.entity.Task;
import com.ajulay.enumirated.Status;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class TaskDAO implements ITaskDAO {

    //private final List<Task> tasks = new ArrayList<>();

    private Connection conn;

    private Task fetch(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final Task task = new Task();
        task.setId(resultSet.getString("id"));
        task.setProjectId(resultSet.getString("project_id"));
        final Instant iterm = resultSet.getTimestamp("term").toInstant();
        final Date term = Date.from(iterm);
        task.setTerm(term);
        task.setPriority(resultSet.getInt("priority"));
        task.setContent(resultSet.getString("task_content"));
        task.setStatus(Status.valueOf(resultSet.getString("status")));
        return task;
    }

    private List<Task> fetchAll(final ResultSet resultSet) throws SQLException {
        final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            final Task task = new Task();
            task.setId(resultSet.getString("id"));
            task.setProjectId(resultSet.getString("project_id"));
            final Instant iTerm = resultSet.getTimestamp("term").toInstant();
            final Date term = Date.from(iTerm);
            task.setTerm(term);
            task.setPriority(resultSet.getInt("priority"));
            task.setContent(resultSet.getString("task_content"));
            task.setStatus(Status.valueOf(resultSet.getString("status")));
            tasks.add(task);
        }
        return tasks;
    }


    @Override
    public Task save(final Task task) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "INSERT INTO task(id, project_id, priority, task_content, term, status) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, task.getId());
            statement.setString(2, task.getProjectId());
            statement.setInt(3, task.getPriority());
            statement.setString(4, task.getContent());
            Timestamp iTerm = Timestamp.from(task.getTerm().toInstant());
            statement.setTimestamp(5, iTerm);
            statement.setString(6, task.getStatus().name());
            statement.execute();
            statement.close();
            return task;
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
    public Task delete(final String id) {
        final Task task = findById(id);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM task WHERE id = '" + id + "'");
            statement.execute();
            statement.close();
            return task;
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
    public Task update(final Task task) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "UPDATE task SET project_id = ?, priority = ?, task_content = ?, term = ?, status = ? WHERE id = ?");
            statement.setString(1, task.getProjectId());
            statement.setInt(2, task.getPriority());
            statement.setString(3, task.getContent());
            final Timestamp iTerm = Timestamp.from(task.getTerm().toInstant());
            statement.setTimestamp(4, iTerm);
            statement.setString(5, task.getStatus().name());
            statement.setString(6, task.getId());
            statement.execute();
            statement.close();
            return task;
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
    public Task findById(final String id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM task WHERE id = '" + id + "'");
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

    public List<Task> findAll() {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM task");
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
    public List<Task> findByProjectId(final String projectId) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM task WHERE project_id = '" + projectId + "'");
            final ResultSet result = statement.executeQuery();
            return fetchAll(result);
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
    public List<Task> merge(List<Task> tasks) {
        for (final Task task : tasks) {
            if (findById(task.getId()) == null) {
                save(task);
                continue;
            }
            update(task);
        }
        return tasks;
    }

    public void setConn(final Connection conn) {
        this.conn = conn;
    }

}
