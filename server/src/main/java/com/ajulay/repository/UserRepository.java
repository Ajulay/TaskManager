package com.ajulay.repository;

import com.ajulay.entity.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

@Repository(forEntity = User.class)
public interface UserRepository extends EntityRepository<User, String> {

    @Query("select u from User u WHERE u.login = ?1")
    User findByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findById(String id);

    @Modifying
    @Query("update User as u set u.surname = ?2 where u.id = ?1")
    void changeSurname(String id, String surname);

}
