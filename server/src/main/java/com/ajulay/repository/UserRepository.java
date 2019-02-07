package com.ajulay.repository;


import com.ajulay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u WHERE u.login = ?1")
    User findByLogin(String login);

    @Modifying
    @Query("update User as u set u.surname = ?2 where u.id = ?1")
    void changeSurname(String id, String surname);

}
