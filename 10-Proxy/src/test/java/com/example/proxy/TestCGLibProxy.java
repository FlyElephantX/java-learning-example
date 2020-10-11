package com.example.proxy;

import com.example.proxy.cglib.LoginProxyFactory;
import com.example.proxy.cglib.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCGLibProxy {

    @Test
    public void testCGLibProxy() {
        LoginService target = new LoginService();
        System.out.println("CGLib代理:" + target.getClass());
        LoginService proxy = (LoginService) new LoginProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.login();
    }
}