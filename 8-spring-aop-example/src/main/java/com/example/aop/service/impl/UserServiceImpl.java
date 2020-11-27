package com.example.aop.service.impl;

import com.example.aop.annotation.Log;
import com.example.aop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void loginIntroduction() {
        System.out.println("userservice---loginIntroduction");
    }

    //    @Log(value = "test")
    @Override
    public String insert(String user) {
        System.out.println("UserServiceImpl---insert");
        if ("elephant".equals(user)) {
            throw new RuntimeException();
        }
        return user;
    }

    @Log(value = "test")
    @Override
    public void testAnnotationAop() {
        System.out.println("UserServiceImpl---testAnnotationAop");
    }
}
