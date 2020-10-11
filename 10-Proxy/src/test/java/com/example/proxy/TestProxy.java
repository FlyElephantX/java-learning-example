package com.example.proxy;

import com.example.proxy.service.UserService;
import com.example.proxy.dynamic.UserProxyFactory;
import com.example.proxy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestProxy {

    @Test
    public void testDynamicProxy() {
        UserService target = new UserServiceImpl();
        System.out.println("动态代理:" + target.getClass());
        UserService proxy = (UserService) new UserProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
