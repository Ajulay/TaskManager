package com.ajulay.repository;

import com.ajulay.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

    @Query("SELECT s FROM Session s WHERE s.signature = ?1")
    Session findBySignature(String signature);

    @Query("SELECT s FROM Session s WHERE s.userId = ?1")
    List<Session> findSessionByUserId(String userId);

}
