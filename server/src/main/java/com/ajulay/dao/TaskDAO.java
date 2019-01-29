package com.ajulay.dao;

import com.ajulay.api.dao.ITaskDAO;
import com.ajulay.entity.Task;
import com.ajulay.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class TaskDAO implements ITaskDAO {

    private SessionFactory sessionFactory;

    @Override
    public Task save(final Task task) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            final Transaction transaction = session.beginTransaction();
            session.persist(task);
            transaction.commit();
            return task;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Task delete(final String id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            final Task task = findById(id);
            session.delete(task);
            transaction.commit();
            return task;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    @Override
    public Task update(Task task) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            final Transaction transaction = session.beginTransaction();
            task = (Task) session.merge(task);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public Task findById(final String id) {
        Session session = null;
        Task task = null;
        try {
            session = sessionFactory.openSession();
            final Transaction transaction = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            final Root<Task> root = criteriaQuery.from(Task.class);
            criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
            final TypedQuery<Task> query = session.createQuery(criteriaQuery);
            task = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return task;
    }

    public List<Task> findAll() {
        Session session = null;
        List<Task> tasks = null;
        try {
            session = sessionFactory.openSession();
            final Transaction transaction = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            final Root<Task> root = criteriaQuery.from(Task.class);
            criteriaQuery = criteriaQuery.select(root);
            final TypedQuery<Task> query = session.createQuery(criteriaQuery);
            tasks = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tasks;
    }

    @Override
    public List<Task> findByProjectId(final String projectId) {
        Session session = null;
        List<Task> tasks = null;
        try {
            session = sessionFactory.openSession();
            final Transaction transaction = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            final Root<Task> root = criteriaQuery.from(Task.class);
            criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("projectId"), projectId));
            final TypedQuery<Task> query = session.createQuery(criteriaQuery);
            tasks = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tasks;
    }

    @Override
    public List<Task> merge(final List<Task> tasks) {
        Session session = null;
        try {
            session = HibernateUtil.factory().openSession();
            for (final Task task : tasks) {
                session.merge(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return tasks;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
