package com.spring.service;

import com.spring.model.User;

import java.util.List;

public interface UserService {

    User findUser(Integer id);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);

    List<User> findAllUsers();
}
