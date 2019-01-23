package com.ajulay.api.dao;

import com.ajulay.entity.Project;
import com.ajulay.exception.checked.NoSuchProjectException;

import java.sql.Connection;
import java.util.List;

/**
 * IProjectDAO defines base data access methods for Project
 */
public interface IProjectDAO {

    Project create(Project project);

    Project delete(String id) throws NoSuchProjectException;

    Project update(Project project);

    Project findById(String id);

    List<Project> findAll();

    List<Project> merge(List<Project> projects);

    void setConn(Connection conn);

}
