package com.ajulay.repository;

import com.ajulay.api.repository.IAssigneeRepository;
import com.ajulay.entity.Assignee;
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

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class AssigneeRepository implements IAssigneeRepository {

    @Inject
    @NotNull
    private EntityManager entityManager;

    @Override
    @NotNull
    public Assignee save(@NotNull final Assignee assignee) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(assignee);
        transaction.commit();
        return assignee;
    }

    @Override
    @NotNull
    public Assignee remove(@NotNull final Assignee assignee) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(assignee);
        transaction.commit();
        return assignee;
    }

    @Override
    @NotNull
    public Assignee update(@NotNull final Assignee assignee) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(assignee);
        transaction.commit();
        return assignee;
    }

    @Override
    @NotNull
    public Assignee findById(@NotNull final String id) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Assignee> criteriaQuery = criteriaBuilder.createQuery(Assignee.class);
        @NotNull final Root<Assignee> root = criteriaQuery.from(Assignee.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        @NotNull final TypedQuery<Assignee> query = entityManager.createQuery(criteriaQuery);
        final Assignee assignee = query.getSingleResult();
        transaction.commit();
        return assignee;
    }

    @Override
    @Nullable
    public List<Assignee> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Assignee> criteriaQuery = criteriaBuilder.createQuery(Assignee.class);
        @NotNull final Root<Assignee> root = criteriaQuery.from(Assignee.class);
        criteriaQuery = criteriaQuery.select(root);
        @NotNull final TypedQuery<Assignee> query = entityManager.createQuery(criteriaQuery);
        final List<Assignee> assignees = query.getResultList();
        transaction.commit();
        return assignees;
    }

    @Override
    @NotNull
    public List<Assignee> findAllByCriteria(@NotNull final Assignee entity) {
        //TODO later
        return Collections.emptyList();
    }

    @Override
    @NotNull
    public List<Assignee> findByUserId(@NotNull final String userId) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Assignee> criteriaQuery = criteriaBuilder.createQuery(Assignee.class);
        @NotNull final Root<Assignee> root = criteriaQuery.from(Assignee.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("userId"), userId));
        @NotNull final TypedQuery<Assignee> query = entityManager.createQuery(criteriaQuery);
        final List<Assignee> assignees = query.getResultList();
        transaction.commit();
        return assignees;
    }

    @Override
    @NotNull
    public List<Assignee> findByTaskId(@NotNull final String taskId) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Assignee> criteriaQuery = criteriaBuilder.createQuery(Assignee.class);
        @NotNull final Root<Assignee> root = criteriaQuery.from(Assignee.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("taskId"), taskId));
        @NotNull final TypedQuery<Assignee> query = entityManager.createQuery(criteriaQuery);
        final List<Assignee> assignees = query.getResultList();
        transaction.commit();
        return assignees;
    }

}
