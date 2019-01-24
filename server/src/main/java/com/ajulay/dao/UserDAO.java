package com.ajulay.dao;

import com.ajulay.api.dao.IUserDAO;
import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;
import com.ajulay.mybatis.mapper.MyBatisUserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class UserDAO implements IUserDAO {

    private SqlSessionFactory sqlSessionFactory;

    private User fetch(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final User user = new User();
        user.setId(resultSet.getString("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password_hash"));
        user.setSurname(resultSet.getString("surname"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("lastname"));
        final String role = resultSet.getString("role");
        if (role != null) user.setRole(Role.valueOf(role));
        return user;
    }

    private List<User> fetchAll(final ResultSet resultSet) throws SQLException {
        final List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            final User user = new User();
            user.setId(resultSet.getString("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password_hash"));
            user.setSurname(resultSet.getString("surname"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastname"));
            user.setRole(Role.valueOf(resultSet.getString("role")));
            users.add(user);
        }
        return users;
    }

    @Override
    public User delete(final String id) {
        final SqlSession session = sqlSessionFactory.openSession();
        final MyBatisUserDao userMapper = session.getMapper(MyBatisUserDao.class);
        final User user = userMapper.findById(id);
        if (user == null) return null;
        int countDeletedUsers = userMapper.delete(id);
        if (countDeletedUsers == 0) return null;
        session.commit();
        session.close();
        return user;
    }

    @Override
    public User update(final User user) {
        final SqlSession session = sqlSessionFactory.openSession();
        final MyBatisUserDao userMapper = session.getMapper(MyBatisUserDao.class);
        int countUpdateUsers = userMapper.update(user);
        if (countUpdateUsers == 0) return null;
        session.commit();
        session.close();
        return user;
    }

    @Override
    public User findById(final String id) {
        final SqlSession session = sqlSessionFactory.openSession();
        final MyBatisUserDao userMapper = session.getMapper(MyBatisUserDao.class);
        final User user = userMapper.findById(id);
        session.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> findAll() {
        final SqlSession session = sqlSessionFactory.openSession();
        final MyBatisUserDao userMapper = session.getMapper(MyBatisUserDao.class);
        final List<User> users = userMapper.findAll();
        session.commit();
        session.close();
        return users;
    }

    @Override
    public User findByLogin(final String login) {
        final SqlSession session = sqlSessionFactory.openSession();
        final MyBatisUserDao userMapper = session.getMapper(MyBatisUserDao.class);
        final User user = userMapper.findByLogin(login);
        session.commit();
        session.close();
        return user;
    }
    @Override
    public User save(final User user) {
        final SqlSession session = sqlSessionFactory.openSession();
        final MyBatisUserDao userMapper = session.getMapper(MyBatisUserDao.class);
        userMapper.save(user);
        session.commit();
        session.close();
        return user;
    }

    @Override
    public void setSqlSessionFactory(final SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


}
