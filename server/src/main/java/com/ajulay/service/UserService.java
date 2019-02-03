package com.ajulay.service;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IUserService;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.User;
import com.ajulay.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class UserService implements IUserService {

    @Nullable
    private User currentUser;

    @Inject
    @NotNull
    private UserRepository userRepository;

    @Inject
    private IAssigneeService assigneeService;

    @Inject
    @NotNull
    private EntityManager entityManager;


    @Override
    @Nullable
    public User createByLogin(@NotNull final String login) {
        if (login.isEmpty()) {
            return null;
        }
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final User user = new User();
        user.setLogin(login);
        @Nullable final User savedUser = userRepository.save(user);
        transaction.commit();
        return savedUser;
    }

    @Override
    @Nullable
    public User removeById(@NotNull final String id) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final User user = userRepository.findById(id);
        userRepository.remove(user);
        transaction.commit();
        return user;
    }

    @Override
    @Nullable
    public User update(@NotNull final User user) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        userRepository.refresh(user);
        transaction.commit();
        return user;
    }

    @Nullable
    public User findById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final User user = userRepository.findById(id);
        transaction.commit();
        return user;
    }

    @Override
    @Nullable
    public User findByLogin(@NotNull final String login) {
        if (login.isEmpty()) {
            return null;
        }
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final User user = userRepository.findByLogin(login);
        transaction.commit();
        return user;
    }

    @Override
    @Nullable
    public User changePassword(@NotNull final User user, @NotNull final String passwordHash) {
        if (passwordHash.isEmpty()) return null;
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final User updatedUser = userRepository.findById(user.getId());
        updatedUser.setPasswordHash(passwordHash);
        transaction.commit();
        return updatedUser;
    }

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    @Nullable
    public User save(@NotNull final User user) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @Nullable final User savedUser = userRepository.save(user);
        transaction.commit();
        return savedUser;
    }

    @Override
    @Nullable
    public User remove(@NotNull final User user) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        userRepository.remove(user);
        transaction.commit();
        return user;
    }

    @Override
    @NotNull
    public List<User> findAll() {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<User> users = userRepository.findAll();
        transaction.commit();
        return users;
    }

    @Override
    @NotNull
    public List<User> updateAll(@NotNull final List<User> users) {
        @NotNull final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (@NotNull final User user : users) {
            entityManager.merge(user);
        }
        transaction.commit();
        return users;
    }

    @Override
    @NotNull
    public List<User> findUserAllByTaskId(@NotNull final String taskId) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        @NotNull final List<Assignee> assignees = assigneeService.findAllByTaskId(taskId);
        @NotNull
        List<User> users = new ArrayList<>();
        for (@NotNull final Assignee assignee : assignees) {
            final User user = userRepository.findById(assignee.getUserId());
            users.add(user);
        }
        transaction.commit();
        return users;
    }

    @Nullable
    public User changeSurname(String id, String surname) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        userRepository.changeSurname(id, surname);
        final User user = userRepository.findById(id);
        transaction.commit();
        return user;
    }

}
