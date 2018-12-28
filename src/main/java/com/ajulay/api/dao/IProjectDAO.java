package com.ajulay.api.dao;

import com.ajulay.entity.Project;
import com.ajulay.exception.checked.NoSuchProjectException;

import java.util.List;

/**
 * IProjectDAO defines base data access methods for Project
 */
public interface IProjectDAO {

    Project create(Project project);

    Project delete(String id) throws NoSuchProjectException;

    Project update(Project project) throws NoSuchProjectException;

    Project findById(String id) throws NoSuchProjectException;

    List<Project> findAll();

    boolean merge(List<Project> projects);
}
