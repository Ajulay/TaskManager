package com.ajulay.service;

import com.ajulay.api.service.IProjectService;
import com.ajulay.entity.Project;
import com.ajulay.repository.ProjectRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Component
@Transactional
public class ProjectService implements IProjectService {

    @Inject
    private ProjectRepository projectRepository;

    @Override
    @Nullable
    public Project findById(@NotNull final String projectId) {
        if (projectId.isEmpty()) {
            return null;
        }
        return projectRepository.findById(projectId).get();
    }

    @Override
    @NotNull
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Nullable
    public Project save(@NotNull final Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Nullable
    public Project createProjectByName(@NotNull final String projectName) {
        if (projectName.isEmpty()) return null;
        final Project project = new Project();
        project.setName(projectName);
        return projectRepository.save(project);
    }

    @Override
    @Nullable
    public Project update(@NotNull final Project project) {
        projectRepository.save(project);
        return project;
    }

    @NotNull
    public List<Project> findAllByUserId(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    @NotNull
    public List<Project> updateAll(@NotNull final List<Project> projects) {
        projectRepository.saveAll(projects);
        return projects;
    }

}
