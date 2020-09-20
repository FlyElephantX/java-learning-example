package com.example.mybatis.mapper;

import com.example.mybatis.model.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    User findUserById(int id);

    void insert(User user);

    void update(User user);

    void delete(int id);
}
