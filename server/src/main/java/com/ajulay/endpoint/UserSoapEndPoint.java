package com.ajulay.endpoint;

import com.ajulay.api.service.IOveralService;
import com.ajulay.api.soap.IUserSoapService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;

import javax.jws.WebService;
import java.util.List;

/**
 * {@inheritDoc}
 */

@WebService
public class UserSoapEndPoint implements IUserSoapService {

    private IOveralService overalService;

    public UserSoapEndPoint() {
    }

    public UserSoapEndPoint(IOveralService overalService) {
        this.overalService = overalService;
    }

    @Override
    public Success deleteUser(final Session session, final String surname) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final User deletedUser = overalService.getUserService().deleteUser(surname);
        if (deletedUser == null) return null;
        return new Success();
    }

    @Override
    public Success updateUser(final Session session, final User user) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final User updatedUser = overalService.getUserService().updateUser(user);
        if (updatedUser == null) return null;
        return new Success();
    }

    @Override
    public User getBySurname(final Session session, final String surname) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getUserService().getBySurname(surname);
    }

    @Override
    public User findById(final Session session, final String id) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getUserService().findById(id);
    }

    @Override
    public List<User> getUsers(final Session session) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getUserService().getUsers();
    }

    @Override
    public Success merge(final Session session, final List<User> users) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final List<User> mergedUsers = overalService.getUserService().merge(users);
        if (mergedUsers == null) return null;
        return new Success();
    }

    @Override
    public User findByLogin(final Session session, final String login) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        if (ServiceConstant.LOG_OUT.equals(login)) {
            overalService.getSessionService().deleteSessionById(session.getId());
        }
        return overalService.getUserService().findByLogin(login);
    }

    @Override
    public Success changePassword(final Session session, final User user, final String password) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final User changedUser = overalService.getUserService().changePassword(user, password);
        if (changedUser == null) return null;
        return new Success();
    }

    public List<User> findUserAllByTaskId(final Session session, final String taskId) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.findUserAllByTaskId(taskId);
    }

}
