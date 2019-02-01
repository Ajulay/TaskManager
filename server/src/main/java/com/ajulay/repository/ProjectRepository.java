package com.ajulay.repository;

import com.ajulay.api.repository.IProjectRepository;
import com.ajulay.entity.Project;
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
public class ProjectRepository implements IProjectRepository {

    @Inject
    @NotNull
    private EntityManager entityManager;

    @Override
    @NotNull
    public Project save(@NotNull final Project project) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(project);
        transaction.commit();
        return project;
    }

    @Override
    @NotNull
    public Project remove(@NotNull final Project project) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(project);
        transaction.commit();
        return project;
    }

    @Override
    @NotNull
    public Project update(@NotNull final Project project) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(project);
        transaction.commit();
        return project;
    }

    @Override
    @Nullable
    public Project findById(@NotNull final String id) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        @NotNull
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
        @NotNull final Root<Project> root = criteriaQuery.from(Project.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        @NotNull final TypedQuery<Project> query = entityManager.createQuery(criteriaQuery);
        @Nullable
        Project project = null;
        try {
            project = query.getSingleResult();
        } catch (NoResultException e) {
            Logger.logMsg(Logger.INFO, "No such project.");
        }
        transaction.commit();
        return project;
    }

    @Override
    public List<Project> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
        @NotNull final Root<Project> root = criteriaQuery.from(Project.class);
        criteriaQuery = criteriaQuery.select(root);
        @NotNull final TypedQuery<Project> query = entityManager.createQuery(criteriaQuery);
        List<Project> projects = Collections.emptyList();
        try {
            projects = query.getResultList();
        } catch (NoResultException e) {
            Logger.logMsg(Logger.INFO, "No such projects.");
        }
        transaction.commit();
        return projects;
    }

    @Override
    @NotNull
    public List<Project> findAllByCriteria(@NotNull final Project entity) {
        //TODO later
        return Collections.emptyList();
    }

}
