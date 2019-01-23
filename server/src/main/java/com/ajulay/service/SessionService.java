package com.ajulay.service;

import com.ajulay.api.dao.ISessionDao;
import com.ajulay.api.service.ISessionService;
import com.ajulay.dao.SessionDao;
import com.ajulay.entity.Session;

import java.util.List;

public class SessionService implements ISessionService {

    private final ISessionDao sessionDao = new SessionDao();
    private Session currentSession;

    @Override
    public Session saveSession(final Session session) {
        if (session == null) return null;
        return sessionDao.save(session);
    }

    @Override
    public Session deleteSessionById(final String id) {
        if (id == null || id.isEmpty()) return null;
        final Session session = sessionDao.findById(id);
        return sessionDao.delete(session);
    }

    @Override
    public List<Session> findSessionAll() {
        return sessionDao.findAll();
    }

    @Override
    public Session findSessionById(final String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) return null;
        return sessionDao.findById(sessionId);
    }

    @Override
    public Session findBySignature(final String signiture) {
        if (signiture == null || signiture.isEmpty()) return null;
        return sessionDao.findBySigniture(signiture);
    }

    @Override
    public Session deleteSessionBySignature(final String signature) {
        if (signature == null || signature.isEmpty()) return null;
        final Session session = sessionDao.findBySigniture(signature);
        return sessionDao.delete(session);
    }

    @Override
    public List<Session> saveSessionAll(final List<Session> sessions) {
        if (sessions == null || sessions.isEmpty()) return null;
        return sessionDao.addSessionAll(sessions);
    }

    @Override
    public List<Session> deleteSessionAll(final List<Session> sessions) {
        if (sessions == null || sessions.isEmpty()) return null;
        return sessionDao.removeAll(sessions);
    }

    @Override
    public List<Session> deleteSessionAll() {
        return sessionDao.deleteAll();
    }

    public List<Session> findSessionByUserId(final String userId) {
        return sessionDao.findSessionByUserId(userId);
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(final Session currentSession) {
        this.currentSession = currentSession;
    }

    public ISessionDao getSessionDao() {
        return sessionDao;
    }

}
