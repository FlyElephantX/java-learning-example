package com.example.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理---开启事务");
        Object returnValue = method.invoke(target, args);
        System.out.println("动态代理---提交事务");
        return null;
    }
}
