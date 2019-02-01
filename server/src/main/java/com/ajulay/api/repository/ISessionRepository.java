package com.ajulay.api.repository;

import com.ajulay.entity.Session;

import java.util.List;

public interface ISessionRepository extends IRepository<Session> {

    List<Session> findSessionByUserId(String userId);

    Session findBySigniture(String signiture);

}
