package com.ajulay.service;

import com.ajulay.api.service.ISessionService;
import com.ajulay.entity.Session;
import com.ajulay.repository.SessionRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class SessionService implements ISessionService {

    @Inject
    private SessionRepository sessionRepository;

    @Nullable
    private Session currentSession;

    @Nullable
    public Session save(@NotNull final Session session) {
        return sessionRepository.save(session);
    }

    @Nullable
    public Session removeById(@NotNull final String id) {
        if (id.isEmpty()) return null;
        @Nullable final Session session = sessionRepository.findById(id).get();
        if (session != null) {
            sessionRepository.delete(session);
        }
        return session;
    }

    @Nullable
    public Session findBySignature(@NotNull final String signiture) {
        if (signiture.isEmpty()) return null;
        return sessionRepository.findBySignature(signiture);
    }

    @Nullable
    public Session removeBySignature(@NotNull final String signature) {
        @Nullable final Session session = sessionRepository.findBySignature(signature);
        if (session != null) {
            sessionRepository.delete(session);
        }
        return session;
    }

    @NotNull
    public List<Session> removeAll() {
        @NotNull final List<Session> sessions = sessionRepository.findAll();
        sessionRepository.deleteAll();
        return sessions;

    }

    @NotNull
    public List<Session> findByUserId(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        return sessionRepository.findSessionByUserId(userId);
    }

    @Nullable
    public Session findById(@NotNull final String id) {
        if (id.isEmpty()) return null;
        return sessionRepository.findById(id).get();
    }

    @Nullable
    public Session remove(@NotNull final Session session) {
        sessionRepository.delete(session);
        return session;
    }

    @Nullable
    public Session update(@NotNull final Session session) {
        sessionRepository.save(session);
        return session;
    }

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

    public void removeAll(@NotNull final List<Session> sessions) {
        for (@NotNull final Session session : sessions) {
            sessionRepository.delete(session);
        }
    }

}
