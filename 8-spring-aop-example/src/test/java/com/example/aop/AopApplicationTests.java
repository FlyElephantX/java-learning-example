package com.example.aop;


import com.example.aop.service.DoSthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.aop.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AopApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testIntroduction() {
        userService.loginIntroduction();
        DoSthService doSthService = (DoSthService) userService;
        doSthService.doSth();
    }

    @Test
    public void testAop1() {
        userService.insert("fly");
    }

    @Test
    public void testAop2() {
        userService.insert("elephant1");
    }

    @Test
    public void testAop3() {
        userService.testAnnotationAop();
    }

}
