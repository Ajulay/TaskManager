package com.ajulay.dao;

import com.ajulay.project.Project;

import java.util.List;

public interface ProjectDao {

    Project create(String projectName);

    Project delete(String id) throws Exception;

    Project update(Project project) throws Exception;

    Project findById(String id) throws Exception;

    List<Project> findAll();

}
