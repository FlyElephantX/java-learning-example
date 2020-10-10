package com.example.aop.aspect;

import com.example.aop.service.DoSthService;
import com.example.aop.service.impl.DoSthServiceImpl;
import com.example.aop.service.impl.UserServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IntroductionAop {

    @DeclareParents(value = "com.example.aop..service..*", defaultImpl = DoSthServiceImpl.class)
    public DoSthService userService;
}
