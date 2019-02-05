package com.ajulay.service;

import com.ajulay.api.service.ISessionService;
import com.ajulay.entity.Session;
import com.ajulay.repository.SessionRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Transactional
public class SessionService implements ISessionService {

    @Inject
    @NotNull
    private SessionRepository sessionRepository;

    @Nullable
    private Session currentSession;

    @Override
    @Nullable
    public Session save(@NotNull final Session session) {
        return sessionRepository.save(session);
    }

    @Override
    @Nullable
    public Session removeById(@NotNull final String id) {
        if (id.isEmpty()) return null;
        @Nullable final Session session = sessionRepository.findBy(id);
        if (session != null) {
            sessionRepository.remove(session);
        }
        return session;
    }

    @Override
    @Nullable
    public Session findBySignature(@NotNull final String signiture) {
        if (signiture.isEmpty()) return null;
        return sessionRepository.findBySignature(signiture);
    }

    @Override
    @Nullable
    public Session removeBySignature(@NotNull final String signature) {
        @Nullable final Session session = sessionRepository.findBySignature(signature);
        if (session != null) {
            sessionRepository.remove(session);
        }
        return session;
    }

    @Override
    @NotNull
    public List<Session> removeAll() {
        @NotNull final List<Session> sessions = sessionRepository.findAll();
        sessionRepository.removeAll();
        return sessions;

    }

    @Override
    @NotNull
    public List<Session> findByUserId(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        return sessionRepository.findSessionByUserId(userId);
    }

    @Override
    @Nullable
    public Session findById(@NotNull final String id) {
        if (id.isEmpty()) return null;
        return sessionRepository.findBy(id);
    }

    @Override
    @Nullable
    public Session remove(@NotNull final Session session) {
        sessionRepository.remove(session);
        return session;
    }

    @Override
    @Nullable
    public Session update(@NotNull final Session session) {
        sessionRepository.refresh(session);
        return session;
    }

    @Override
    @NotNull
    public List<Session> findAll() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions;
    }

    @Nullable
    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(@Nullable final Session currentSession) {
        this.currentSession = currentSession;
    }

    @Override
    public void removeAll(@NotNull final List<Session> sessions) {
        for (@NotNull final Session session : sessions) {
            sessionRepository.remove(session);
        }
    }

}
