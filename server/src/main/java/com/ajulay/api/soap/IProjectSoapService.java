package com.ajulay.api.soap;

import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Project;
import com.ajulay.entity.Session;
import com.ajulay.exception.checked.NoSuchProjectException;

import java.util.List;

/**
 * IProjectService creates conditions for CRUD operations for Project
 */
public interface IProjectSoapService {

    Project getByName(final Session session, String projectName) throws NoSuchProjectException;

    Project getById(final Session session, String projectId);

    List<Project> getProjects(Session session);

    Success saveProject(final Session session, Project project);

    Success merge(final Session session, List<Project> projects);

    Success createProjectByName(final Session session, String projectName);

}
