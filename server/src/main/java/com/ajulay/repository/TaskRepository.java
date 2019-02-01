package com.ajulay.repository;

import com.ajulay.api.repository.ITaskRepository;
import com.ajulay.entity.Task;
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
public class TaskRepository implements ITaskRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    @NotNull
    public Task save(@NotNull final Task task) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(task);
        transaction.commit();
        return task;
    }

    @Override
    @NotNull
    public Task remove(@NotNull final Task task) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(task);
        transaction.commit();
        return task;
    }

    @Override
    @NotNull
    public Task update(@NotNull final Task task) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(task);
        transaction.commit();
        return task;
    }

    @Override
    @Nullable
    public Task findById(@NotNull final String id) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        @NotNull final Root<Task> root = criteriaQuery.from(Task.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        @NotNull final TypedQuery<Task> query = entityManager.createQuery(criteriaQuery);
        Task task = null;
        try {
            task = query.getSingleResult();
        } catch (NoResultException e) {
            Logger.logMsg(Logger.INFO, "No such task.");
        }
        transaction.commit();
        return task;
    }

    @Override
    @NotNull
    public List<Task> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        @NotNull final Root<Task> root = criteriaQuery.from(Task.class);
        criteriaQuery = criteriaQuery.select(root);
        @NotNull final TypedQuery<Task> query = entityManager.createQuery(criteriaQuery);
        List<Task> tasks = Collections.emptyList();
        try {
            tasks = query.getResultList();
        } catch (NoResultException e) {
            Logger.logMsg(Logger.INFO, "No such tasks.");
        }
        transaction.commit();
        return tasks;
    }

    @Override
    @NotNull
    public List<Task> findAllByCriteria(@NotNull final Task entity) {
        //TODO later
        return Collections.emptyList();
    }

}
