package com.ajulay.dao;

import com.ajulay.api.dao.ISessionDao;
import com.ajulay.entity.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionDao implements ISessionDao {

    private final List<Session> sessions = new ArrayList<>();

    @Override
    public Session save(final Session session) {
        sessions.add(session);
        return session;
    }

    @Override
    public Session delete(final Session session) {
        sessions.remove(session);
        return session;
    }

    @Override
    public Session findById(final String id) {
        for (final Session session : sessions) {
            if (session.getId().equals(id)) {
                return session;
            }
        }
        return null;
    }

    @Override
    public Session findBySigniture(final String signiture) {
        for (final Session session : sessions) {
            if (session.getSignature().equals(signiture)) {
                return session;
            }
        }
        return null;
    }

    @Override
    public List<Session> findAll() {
        return sessions;
    }

    @Override
    public List<Session> addSessionAll(final List<Session> sessions) {
        this.sessions.addAll(sessions);
        return sessions;
    }

    @Override
    public List<Session> removeAll(final List<Session> sessions) {
        this.sessions.removeAll(sessions);
        return sessions;
    }

    @Override
    public List<Session> findSessionByUserId(final String userId) {
        final List<Session> selectedSessions = new ArrayList<>();
        for (final Session session : sessions) {
            if (userId.equals(session.getUserId())) {
                selectedSessions.add(session);
            }
        }
        return selectedSessions;
    }

}
