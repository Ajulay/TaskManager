package com.ajulay.api.dao;

import com.ajulay.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * IUserDAO defines base data access methods for User
 */
public interface IUserDAO {

    User delete(String id);

    User update(User assigner);

    User findById(String id);

    List<User> findAll();

    User findByLogin(String login);

    User save(User user);

    void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);

}
