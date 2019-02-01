package com.ajulay.api.service;

import com.ajulay.entity.Session;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ISessionService {

    Session save(Session session);

    Session removeById(String id);

    Session findBySignature(String signiture);

    Session removeBySignature(String signiture);

    List<Session> removeAll();

    List<Session> findByUserId(String userId);

    Session findById(String id);

    Session remove(Session entity);

    Session update(Session entity);

    List<Session> findAll();

    Session getCurrentSession();

    void setCurrentSession(@Nullable Session currentSession);

    void removeAll(List<Session> sessionAllForRemove);
}
