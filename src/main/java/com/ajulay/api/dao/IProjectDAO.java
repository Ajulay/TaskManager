package com.ajulay.api.dao;

import com.ajulay.entity.Project;

import java.util.List;

/**
 * IProjectDAO defines base data access methods for Project
 */
public interface IProjectDAO {

    Project create(String projectName);

    Project delete(String id) throws Exception;

    Project update(Project project) throws Exception;

    Project findById(String id) throws Exception;

    List<Project> findAll();

}
