package com.example.proxy.service.impl;

import com.example.proxy.service.UserService;

public class UserServiceProxy implements UserService {

    private UserService target;

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开启事务");
        target.save();
        System.out.println("提交事务");
    }
}
