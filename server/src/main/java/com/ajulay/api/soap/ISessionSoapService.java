package com.ajulay.api.soap;

import com.ajulay.endpoint.transport.Success;
import com.ajulay.entity.Session;

import java.util.List;

/**
 * ISessionSoapService for user login and register.
 */
public interface ISessionSoapService {

    Session login(String login, String passwordHash) throws Exception;

    Success register(String login, String passwordHash);

    Success logout(Session session);

    List<Session> getSessionAll(Session session);

    Success deleteSessionById(Session session, String sessionId);

}
