package com.ajulay.repository;

import com.ajulay.api.repository.IRepository;
import com.ajulay.entity.Session;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Session.class)
public interface SessionRepository extends IRepository<Session> {

    @Query("SELECT s FROM Session s WHERE s.signature = ?1")
    Session findBySignature(String signature);

    @Query("SELECT s FROM Session s WHERE s.userId = ?1")
    List<Session> findSessionByUserId(String userId);

    Session save(Session entity);

    @Query("SELECT s FROM Session s WHERE s.id = ?1")
    Session findById(String id);

    Session remove(Session entity);

    Session update(Session entity);

    List<Session> findAll();

}
