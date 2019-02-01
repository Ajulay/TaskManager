package com.ajulay.repository;

import com.ajulay.api.repository.IUserRepository;
import com.ajulay.entity.User;
import com.sun.media.jfxmedia.logging.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class UserRepository implements IUserRepository {

    @Inject
    @NotNull
    private EntityManager entityManager;

    @Override
    @NotNull
    public User remove(@NotNull final User user) {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();
        return user;
    }

    @Override
    @NotNull
    public User update(@NotNull final User user) {
        @NotNull
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
        return user;
    }

    @Override
    @Nullable
    public User findById(@NotNull final String id) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        @NotNull final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        @NotNull final TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            Logger.logMsg(Logger.INFO, "No such user.");
        }
        transaction.commit();
        return user;
    }

    @Override
    @NotNull
    public List<User> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        @NotNull final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery = criteriaQuery.select(root);
        @NotNull final TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        List<User> users = Collections.emptyList();
        try {
            users = query.getResultList();
        } catch (NoResultException e) {
            Logger.logMsg(Logger.INFO, "No such users.");
        }
        transaction.commit();
        return users;
    }

    @Override
    @NotNull
    public List<User> findAllByCriteria(@NotNull final User entity) {
        //TODO later
        return Collections.emptyList();
    }

    @Override
    @Nullable
    public User findByLogin(@NotNull final String login) {
        if (login.isEmpty()) return null;
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        @NotNull final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login));
        @NotNull final TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            Logger.logMsg(Logger.INFO, "No such user.");
        }
        transaction.commit();
        return user;
    }

    @Override
    @Nullable
    public User save(@NotNull final User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return user;
    }

}
