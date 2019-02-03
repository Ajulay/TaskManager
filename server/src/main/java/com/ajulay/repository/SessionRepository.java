package com.ajulay.repository;

import com.ajulay.entity.Session;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Session.class)
public interface SessionRepository extends EntityRepository<Session, String> {

    @Query("SELECT s FROM Session s WHERE s.signature = ?1")
    Session findBySignature(String signature);

    @Query("SELECT s FROM Session s WHERE s.userId = ?1")
    List<Session> findSessionByUserId(String userId);

}
