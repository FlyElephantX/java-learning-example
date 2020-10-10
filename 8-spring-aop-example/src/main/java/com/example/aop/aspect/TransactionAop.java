package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAop {

    @Pointcut("execution(* com.example..service.*Service.insert(String))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void beginTransaction() {
        System.out.println("TransactionAop---before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("TransactionAop---after");
    }

    @AfterReturning(value = "pointcut()", returning = "obj")
    public void afterReturning(Object obj) {
        System.out.println("TransactionAop---afterReturning:" + obj);
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("TransactionAop---afterThrowing rollback");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        try {
            System.out.println("TransactionAop---around before:" + method);
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("TransactionAop---around after:" + method);
        }
    }

}
