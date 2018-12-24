package com.ajulay.api.dao;

import com.ajulay.entity.Project;

import java.util.List;

public interface IProjectDao {

    Project create(String projectName);

    Project delete(String id) throws Exception;

    Project update(Project project) throws Exception;

    Project findById(String id) throws Exception;

    List<Project> findAll();

}
