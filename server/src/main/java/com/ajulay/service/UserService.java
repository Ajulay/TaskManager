package com.ajulay.service;

import com.ajulay.api.service.IAssigneeService;
import com.ajulay.api.service.IUserService;
import com.ajulay.entity.Assignee;
import com.ajulay.entity.User;
import com.ajulay.repository.UserRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
@Transactional
public class UserService implements IUserService {

    @Nullable
    private User currentUser;

    @Inject
    @NotNull
    private UserRepository userRepository;

    @Inject
    private IAssigneeService assigneeService;

    @Override
    @Nullable
    public User createByLogin(@NotNull final String login) {
        if (login.isEmpty()) {
            return null;
        }
        final User user = new User();
        user.setLogin(login);
        @Nullable final User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    @Nullable
    public User removeById(@NotNull final String id) {
        final User user = userRepository.findById(id);
        userRepository.remove(user);
        return user;
    }

    @Override
    @Nullable
    public User update(@NotNull final User user) {
        userRepository.save(user);
        return user;
    }

    @Nullable
    public User findById(@NotNull final String id) {
        if (id.isEmpty()) {
            return null;
        }
        return userRepository.findById(id);
    }

    @Override
    @Nullable
    public User findByLogin(@NotNull final String login) {
        if (login.isEmpty()) {
            return null;
        }
        User user = null;
        try {
            user = userRepository.findByLogin(login);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    @Nullable
    public User changePassword(@NotNull final User user, @NotNull final String passwordHash) {
        if (passwordHash.isEmpty()) return null;
        user.setPasswordHash(passwordHash);
        userRepository.refresh(user);
        return user;
    }

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(@Nullable User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    @Nullable
    public User save(@NotNull final User user) {
        return userRepository.save(user);
    }

    @Override
    @Nullable
    public User remove(@NotNull final User user) {
        userRepository.remove(user);
        return user;
    }

    @Override
    @NotNull
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @NotNull
    public List<User> updateAll(@NotNull final List<User> users) {
        for (@NotNull final User user : users) {
            userRepository.refresh(user); //todo 1 request
        }
        return users;
    }

    @Override
    @NotNull
    public List<User> findUserAllByTaskId(@NotNull final String taskId) {
        @NotNull final List<Assignee> assignees = assigneeService.findAllByTaskId(taskId);
        final List<User> users = new ArrayList<>();
        for (@NotNull final Assignee assignee : assignees) {
            final User user = userRepository.findById(assignee.getUserId());
            users.add(user);
        }
        return users;
    }

    @Nullable
    public User changeSurname(String userId, String surname) {
        userRepository.changeSurname(userId, surname);
        return userRepository.findById(userId);
    }

}
