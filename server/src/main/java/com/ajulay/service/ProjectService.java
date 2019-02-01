package com.ajulay.service;

import com.ajulay.api.repository.IProjectRepository;
import com.ajulay.api.service.IProjectService;
import com.ajulay.entity.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class ProjectService implements IProjectService {

    @Inject
    @NotNull
    private IProjectRepository projectRepository;

    @Inject
    @NotNull
    private EntityManager entityManager;

    @Override
    @Nullable
    public Project findById(@NotNull final String projectId) {
        if (projectId.isEmpty()) {
            return null;
        }
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Project project = projectRepository.findById(projectId);
        transaction.commit();
        return project;
    }

    @Override
    @NotNull
    public List<Project> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Project> projects = projectRepository.findAll();
        transaction.commit();
        return projects;
    }

    @Override
    @Nullable
    public Project save(@NotNull final Project project) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Project savedProject = projectRepository.save(project);
        transaction.commit();
        return savedProject;
    }

    @Override
    @Nullable
    public Project createProjectByName(@NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final Project project = new Project();
        project.setName(projectName);
        @Nullable final Project savedProject = projectRepository.save(project);
        transaction.commit();
        return savedProject;
    }

    @Override
    @Nullable
    public Project update(@NotNull final Project project) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Project updatedProject = projectRepository.update(project);
        transaction.commit();
        return updatedProject;
    }

    @NotNull
    public List<Project> findAllByUserId(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Project> userProjects = projectRepository.findAll().stream()
                .filter((project) -> project.getAuthorId().equals(userId))
                .collect(Collectors.toList());
        transaction.commit();
        return userProjects;
    }

    @Override
    @NotNull
    public List<Project> updateAll(@NotNull final List<Project> projects) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (@NotNull final Project project : projects) {
            entityManager.merge(project);
        }
        transaction.commit();
        return projects;
    }

}
