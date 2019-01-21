package com.ajulay.api.service;

import com.ajulay.entity.Session;

import java.util.List;

public interface ISessionService {

    Session saveSession(Session session);

    Session deleteSessionById(String id);

    List<Session> findSessionAll();

    Session findSessionById(String sessionId);

    Session findBySignature(String signiture);

    Session deleteSessionBySignature(String signiture);

    List<Session> saveSessionAll(List<Session> sessions);

    List<Session> deleteSessionAll(List<Session> sessionAllForRemove);

    Session getCurrentSession();

    void setCurrentSession(Session currentSession);

    List<Session> findSessionByUserId(final String userId);

}
