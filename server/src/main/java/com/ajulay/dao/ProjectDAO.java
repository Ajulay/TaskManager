package com.ajulay.dao;

import com.ajulay.api.dao.IProjectDAO;
import com.ajulay.entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class ProjectDAO implements IProjectDAO {

    // private final List<Project> projects = new ArrayList<>();

    private Connection conn;

    private Project fetch(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final Project project = new Project();
        project.setId(resultSet.getString("id"));
        project.setName(resultSet.getString("name"));
        project.setAuthorId(resultSet.getString("author_id"));
        return project;
    }

    private List<Project> fetchAll(final ResultSet resultSet) throws SQLException {
        final List<Project> users = new ArrayList<>();
        while (resultSet.next()) {
            final Project project = new Project();
            project.setId(resultSet.getString("id"));
            project.setName(resultSet.getString("name"));
            project.setAuthorId(resultSet.getString("author_id"));
            users.add(project);
        }
        return users;
    }

    @Override
    public Project create(final Project project) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "INSERT INTO project (id, name, author_id) VALUES (?, ?, ?)");
            statement.setString(1, project.getId());
            statement.setString(2, project.getName());
            statement.setString(3, project.getAuthorId());
            statement.execute();
            statement.close();
            return project;
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
    public Project delete(final String id) {
        final Project project = findById(id);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM project WHERE id = '" + id + "'");
            statement.execute();
            statement.close();
            return project;
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
    public Project update(final Project project) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "UPDATE project SET name = ?, author_id = ? WHERE id = ?");
            statement.setString(1, project.getName());
            statement.setString(2, project.getAuthorId());
            statement.setString(3, project.getId());
            statement.execute();
            statement.close();
            return project;
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
    public Project findById(final String id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM project WHERE id = '" + id + "'");
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
    public List<Project> findAll() {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(
                    "SELECT * FROM project");
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
    public List<Project> merge(final List<Project> projects) {
        for (final Project project : projects) {
            if (findById(project.getId()) == null) {
                create(project);
                continue;
            }
            update(project);
        }
        return projects;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
