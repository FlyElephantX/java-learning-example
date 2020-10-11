package com.example.proxy.service.impl;

import com.example.proxy.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
