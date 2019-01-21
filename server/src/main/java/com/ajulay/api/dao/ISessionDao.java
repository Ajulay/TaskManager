package com.ajulay.api.dao;

import com.ajulay.entity.Session;

import java.util.List;

public interface ISessionDao {

    Session save(Session session);

    Session delete(Session session);

    Session findById(String id);

    Session findBySigniture(String signiture);

    List<Session> findAll();

    List<Session> addSessionAll(List<Session> sessions);

    List<Session> removeAll(List<Session> sessions);

    List<Session> findSessionByUserId(String userId);

}
