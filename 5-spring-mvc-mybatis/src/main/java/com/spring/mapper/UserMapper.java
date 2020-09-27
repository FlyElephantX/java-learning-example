package com.spring.mapper;

import com.spring.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {

    User findUser(Integer id);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);

    List<User> findAllUsers();
}
