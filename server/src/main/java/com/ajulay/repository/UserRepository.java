package com.ajulay.repository;

import com.ajulay.api.repository.IRepository;
import com.ajulay.entity.User;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = User.class)
public interface UserRepository extends IRepository<User> {

    @Query("select u from User u WHERE u.login = ?1")
    User findByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findById(String id);

    User save(User entity);

    User remove(User entity);

    User update(User entity);

    List<User> findAll();

}
