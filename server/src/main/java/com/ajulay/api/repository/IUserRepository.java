package com.ajulay.api.repository;

import com.ajulay.entity.User;

/**
 * IUserDAO defines base data access methods for User
 */
public interface IUserRepository extends IRepository<User> {

    User findByLogin(String login);

}
