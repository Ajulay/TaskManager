package com.ajulay.endpoint;

import com.ajulay.api.service.IOveralService;
import com.ajulay.api.soap.ISessionSoapService;
import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;
import com.ajulay.entity.User;
import com.ajulay.exception.checked.NoSuchAssignerException;
import com.ajulay.exception.unchecked.LoginExistsException;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public class SessionSoapEndPoint implements ISessionSoapService {

    private IOveralService overalService;


    public SessionSoapEndPoint(IOveralService sessionService) {
        this.overalService = sessionService;
    }

    public SessionSoapEndPoint() {
    }

    @Override
    public Session login(final String login, final String passwordHash) throws Exception {
        final User user = overalService.getUserService().findByLogin(login);
        if (user == null) throw new NoSuchAssignerException();
        if (!user.getPassword().equals(passwordHash)) return null;
        final Session session = new Session();
        session.setUserId(user.getId());
        session.setCreatedDate(new Date());
        session.setSignature(user.getPassword());
        overalService.getSessionService().saveSession(session);
        return session;
    }

    @Override
    public Success register(final String login, final String passwordHash) {
        final User user = overalService.getUserService().createUser(login);
        if (user == null) throw new LoginExistsException();
        user.setPassword(passwordHash);
        user.setSurname(login);
        overalService.getUserService().mergeUser(user);
        return new Success();
    }

    @Override
    public Success logout(final Session session) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        overalService.getSessionService().deleteSessionById(session.getId());
        return new Success();
    }

    @Override
    public List<Session> getSessionAll(final Session session) {
        final Session currentSession = overalService.getSessionService().findSessionById(session.getId());
        if (!currentSession.getSignature().equals(session.getSignature())) {
            return null;
        }
        return overalService.getSessionService().findSessionByUserId(session.getUserId());
    }

    @Override
    public Success deleteSessionById(final Session session, final String sessionId) {
        final Session deleteSession = overalService.getSessionService().deleteSessionById(sessionId);
        if (deleteSession == null) return null;
        return new Success();
    }

}
