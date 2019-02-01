package com.ajulay.service;

import com.ajulay.api.repository.ISessionRepository;
import com.ajulay.api.service.ISessionService;
import com.ajulay.entity.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SessionService implements ISessionService {

    @Inject
    @NotNull
    private ISessionRepository sessionRepository;

    @Inject
    @NotNull
    private EntityManager entityManager;

    @Nullable
    private Session currentSession;

    @Override
    @Nullable
    public Session save(@NotNull final Session session) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Session savedSession = sessionRepository.save(session);
        transaction.commit();
        return savedSession;
    }

    @Override
    @Nullable
    public Session removeById(@NotNull final String id) {
        if (id.isEmpty()) return null;
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Session session = sessionRepository.findById(id);
        if (session != null) {
            sessionRepository.remove(session);
        }
        transaction.commit();
        return session;
    }

    @Override
    @Nullable
    public Session findBySignature(@NotNull final String signiture) {
        if (signiture.isEmpty()) return null;
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Session session = sessionRepository.findBySigniture(signiture);
        transaction.commit();
        return session;
    }

    @Override
    @Nullable
    public Session removeBySignature(@NotNull final String signature) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Session session = sessionRepository.findBySigniture(signature);
        if (session != null) {
            sessionRepository.remove(session);
        }
        transaction.commit();
        return session;
    }

    @Override
    @NotNull
    public List<Session> removeAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Session> sessions = sessionRepository.findAll();
        for (@NotNull final Session session : sessions) {
            sessionRepository.remove(session);
        }
        transaction.commit();
        return sessions;

    }

    @Override
    @NotNull
    public List<Session> findByUserId(@NotNull final String userId) {
        if (userId.isEmpty()) return Collections.emptyList();
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Session> sessions = sessionRepository.findSessionByUserId(userId);
        transaction.commit();
        return sessions;
    }

    @Override
    @Nullable
    public Session findById(@NotNull final String id) {
        if (id.isEmpty()) return null;
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Session session = sessionRepository.findById(id);
        transaction.commit();
        return session;
    }

    @Override
    @Nullable
    public Session remove(@NotNull final Session session) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Session removedSession = sessionRepository.remove(session);
        transaction.commit();
        return removedSession;
    }

    @Override
    @Nullable
    public Session update(@NotNull final Session session) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final Session updatedSession = sessionRepository.update(session);
        transaction.commit();
        return updatedSession;
    }

    @Override
    @NotNull
    public List<Session> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Session> sessions = sessionRepository.findAll();
        transaction.commit();
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

        }
    }


}
