package com.ajulay.endpoint;

import com.ajulay.api.service.IProjectService;
import com.ajulay.api.service.ISessionService;
import com.ajulay.api.soap.IProjectSoapService;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Project;
import com.ajulay.entity.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

@WebService
@Component
public class ProjectSoapEndPoint implements IProjectSoapService {

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IProjectService projectService;


    @Override
    @Nullable
    public Project getByName(@NotNull final Session session, @NotNull final String projectId) {
        @Nullable final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return projectService.findById(projectId);
    }

    @Override
    @Nullable
    public Project getById(final Session session, final String projectId) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return projectService.findById(projectId);

    }

    @Override
    @Nullable
    public List<Project> getProjects(final Session session) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return projectService.findAllByUserId(session.getUserId());
    }

    @Override
    @Nullable
    public Success saveProject(final Session session, final Project project) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Project savedProject = projectService.save(project);
        if (savedProject == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Success merge(final Session session, final List<Project> projects) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        @NotNull final List<Project> mergedProjects = projectService.updateAll(projects);
        if (mergedProjects.isEmpty()) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Success createProjectByName(final Session session, final String projectName) {
        System.out.println(session.getId());
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Project project = new Project();
        project.setAuthorId(session.getUserId());
        project.setName(projectName);
        final Project createdProject = projectService.save(project);
        if (createdProject == null) return null;
        return new Success();
    }

}
