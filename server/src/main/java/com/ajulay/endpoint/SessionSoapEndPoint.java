package com.ajulay.endpoint;

import com.ajulay.api.soap.ISessionSoapService;
import com.ajulay.controller.util.PropertyReader;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.unchecked.LoginExistsException;
import com.ajulay.service.SessionService;
import com.ajulay.service.UserService;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public class SessionSoapEndPoint implements ISessionSoapService {

    @Inject
    private SessionService sessionService;

    @Inject
    private UserService userService;

    @Override
    @Nullable
    public Session login(final String login, final String passwordHash) throws Exception {
        final User user = userService.findByLogin(login);
        if (user == null) throw new NoSuchAssignerException();
        if (!user.getPasswordHash().equals(passwordHash)) return null;
        final Session session = new Session();
        session.setUserId(user.getId());
        session.setCreatedDate(new Date());
        session.setSignature(PropertyReader.SessionSignature.sign(user.getId()));//TODO Property
        sessionService.save(session);
        return session;
    }

    @Override
    public Success register(final String login, final String passwordHash) {
        final User user = userService.createByLogin(login);
        if (user == null) throw new LoginExistsException();
        user.setPasswordHash(passwordHash);
        user.setSurname(login);
        userService.update(user);
        return new Success();
    }

    @Override
    @Nullable
    public Success logout(final Session session) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        sessionService.removeById(session.getId());
        return new Success();
    }

    @Override
    @Nullable
    public List<Session> getSessionAll(final Session session) {
        final Session currentSession = sessionService.findById(session.getId());
        if (currentSession == null) return null;
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return sessionService.findByUserId(session.getUserId());
    }

    @Override
    @Nullable
    public Success deleteSessionById(final Session session, final String sessionId) {
        final Session deleteSession = sessionService.removeById(sessionId);
        if (deleteSession == null) return null;
        return new Success();
    }

}
