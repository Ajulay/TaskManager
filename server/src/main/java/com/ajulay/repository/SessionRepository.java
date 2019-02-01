package com.ajulay.repository;

import com.ajulay.api.repository.ISessionRepository;
import com.ajulay.entity.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SessionRepository implements ISessionRepository {

    @Inject
    @NotNull
    private EntityManager entityManager;

    @Override
    @NotNull
    public Session save(@NotNull final Session session) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(session);
        transaction.commit();
        return session;
    }

    @Override
    @NotNull
    public Session remove(@NotNull final Session session) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(session);
        transaction.commit();
        return session;
    }

    @Override
    @NotNull
    public Session update(@NotNull final Session session) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(session);
        transaction.commit();
        return session;
    }

    @Override
    @Nullable
    public Session findById(@NotNull final String id) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Session> criteriaQuery = criteriaBuilder.createQuery(Session.class);
        @NotNull final Root<Session> root = criteriaQuery.from(Session.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        @NotNull final TypedQuery<Session> query = entityManager.createQuery(criteriaQuery);
        final Session session = query.getSingleResult();
        transaction.commit();
        return session;
    }

    @Override
    @NotNull
    public Session findBySigniture(@NotNull final String signature) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Session> criteriaQuery = criteriaBuilder.createQuery(Session.class);
        @NotNull final Root<Session> root = criteriaQuery.from(Session.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("signature"), signature));
        @NotNull final TypedQuery<Session> query = entityManager.createQuery(criteriaQuery);
        final Session session = query.getSingleResult();
        transaction.commit();
        return session;
    }

    @Override
    @NotNull
    public List<Session> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Session> criteriaQuery = criteriaBuilder.createQuery(Session.class);
        @NotNull final Root<Session> root = criteriaQuery.from(Session.class);
        criteriaQuery = criteriaQuery.select(root);
        @NotNull final TypedQuery<Session> query = entityManager.createQuery(criteriaQuery);
        @NotNull final List<Session> sessions = query.getResultList();
        transaction.commit();
        return sessions;
    }

    @Override
    @NotNull
    public List<Session> findAllByCriteria(@NotNull final Session entity) {
        //TODO later
        return Collections.emptyList();
    }

    @Override
    @NotNull
    public List<Session> findSessionByUserId(@NotNull final String userId) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Session> criteriaQuery = criteriaBuilder.createQuery(Session.class);
        @NotNull final Root<Session> root = criteriaQuery.from(Session.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("userId"), userId));
        @NotNull final TypedQuery<Session> query = entityManager.createQuery(criteriaQuery);
        @NotNull final List<Session> sessions = query.getResultList();
        transaction.commit();
        return sessions;
    }

}
