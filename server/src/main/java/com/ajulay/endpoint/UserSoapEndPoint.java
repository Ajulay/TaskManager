package com.ajulay.endpoint;

import com.ajulay.api.service.ISessionService;
import com.ajulay.api.service.IUserService;
import com.ajulay.api.soap.IUserSoapService;
import com.ajulay.constants.ServiceConstant;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

/**
 * {@inheritDoc}
 */

@WebService
@Component
public class UserSoapEndPoint implements IUserSoapService {

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IUserService userService;

    @Override
    @Nullable
    public Success deleteUser(final Session session, final String id) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final User deletedUser = userService.removeById(id);
        if (deletedUser == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public Success updateUser(final Session session, final User user) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final User updatedUser = userService.update(user);
        if (updatedUser == null) return null;
        return new Success();
    }

    @Override
    @Nullable
    public User getBySurname(final Session session, final String id) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return userService.findById(id);
    }

    @Override
    @Nullable
    public User findById(final Session session, final String id) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return userService.findById(id);
    }

    @Override
    @Nullable
    public List<User> getUsers(final Session session) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return userService.findAll();
    }

    @Override
    @Nullable
    public Success merge(final Session session, final List<User> users) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final List<User> mergedUsers = userService.updateAll(users);
        if (mergedUsers == null) return null;
        return new Success();
    }

    @Override
    public User findByLogin(final Session session, final String login) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        if (ServiceConstant.LOG_OUT.equals(login)) {
            sessionService.remove(session);
        }
        return userService.findByLogin(login);
    }

    @Override
    @Nullable
    public Success changePassword(final Session session, final User user, final String password) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        final User changedUser = userService.changePassword(user, password);
        if (changedUser == null) return null;
        return new Success();
    }

    public List<User> findUserAllByTaskId(final Session session, final String taskId) {
        final Session currentSession = sessionService.findById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return userService.findUserAllByTaskId(taskId);
    }

}
