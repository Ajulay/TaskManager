package com.ajulay.mybatis.mapper;

import com.ajulay.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MyBatisUserDao {

    String INSERT_USER = "INSERT INTO \"user\"(id, login, role, surname, password_hash) " +
            "VALUES (#{id}, #{login}, #{role}, #{surname}, #{password})";
    String GET_USER_BY_ID = "SELECT * FROM \"user\" WHERE id = #{id}";
    String GET_USER_BY_LOGIN = "SELECT * FROM \"user\" WHERE login = #{login}";
    String GET_USER_ALL = "SELECT * FROM \"user\"";
    String UPDATE_USER = "UPDATE \"user\" SET login = #{login}, password_hash = #{password}, surname = #{surname}, role = #{role} WHERE id = #{id}";
    String DELETE_USER = "DELETE FROM user WHERE id = #{id}";


    @Delete(DELETE_USER)
    int delete(String id);


    @Update(UPDATE_USER)
    int update(User user);


    @Select(GET_USER_BY_ID)
    @Results(value = {
            @Result(property = "password", column = "password_hash"),
    }
    )
    User findById(String id);


    @Select(GET_USER_ALL)
    @Results(value = {
            @Result(property = "password", column = "password_hash"),
    }
    )
    List<User> findAll();


    @Insert(INSERT_USER)
    int save(User user);

    @Select(GET_USER_BY_LOGIN)
    @Results(value = {
            @Result(property = "password", column = "password_hash"),
    }
    )
    User findByLogin(@Param("login") String login);

    //    User create(String login);
//
//
//    List<User> merge(List<User> assigners);
//
//
//    User findByLogin(String login);


//    void setConn(Connection conn);

    //    @Select(GET_PERSON_BY_ID)
//    public User doSelectPerson(String userId);
//
//    @Update(UPDATE_PERSON)
//    public User doUpdatePerson(PersonVO vo) throws Exception;
//
//
//    @Insert(INSERT_PERSON)@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true)
//    public int doCreatePerson(PersonVO person) throws Exception;
//
//    @Delete(DELETE_PERSON)@Options(flushCache = true)
//    public int doDeletePerson(long personId) throws Exception;
}
