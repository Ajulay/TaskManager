package com.ajulay.endpoint;

import com.ajulay.api.service.IOveralService;
import com.ajulay.api.soap.IProjectSoapService;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Project;
import com.ajulay.entity.Session;

import javax.jws.WebService;
import java.util.List;

@WebService
public class ProjectSoapEndPoint implements IProjectSoapService {

    private IOveralService overalService;

    public ProjectSoapEndPoint(IOveralService overalService) {
        this.overalService = overalService;
    }

    public ProjectSoapEndPoint() {
    }

    @Override
    public Project getByName(final Session session, final String projectName) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getProjectService().getByName(projectName);
    }

    @Override
    public Project getById(final Session session, final String projectId) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getProjectService().getById(projectId);

    }

    @Override
    public List<Project> getProjects(final Session session) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getProjectService().findProjectByUserId(session.getUserId());
    }

    @Override
    public Success saveProject(final Session session, final Project project) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Project savedProject = overalService.getProjectService().saveProject(project);
        if (savedProject == null) return null;
        return new Success();
    }

    @Override
    public Success merge(final Session session, final List<Project> projects) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final List<Project> mergedProjects = overalService.getProjectService().merge(projects);
        if (mergedProjects == null || mergedProjects.isEmpty()) return null;
        return new Success();
    }

    @Override
    public Success createProjectByName(final Session session, final String projectName) {
        System.out.println(session.getId());
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());

        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final Project createdProject = overalService.createProjectByName(projectName, session.getUserId());
        if (createdProject == null) return null;
        return new Success();
    }

}
