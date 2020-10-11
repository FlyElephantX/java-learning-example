package com.example.proxy;

import com.example.proxy.service.UserService;
import com.example.proxy.service.impl.UserServiceImpl;
import com.example.proxy.service.impl.UserServiceProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StaticUserProxy {

    @Test
    public void testStaticProxy() {
        UserService target = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(target);
        proxy.save();
    }
}
