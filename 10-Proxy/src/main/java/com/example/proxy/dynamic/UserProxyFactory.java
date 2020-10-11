package com.example.proxy.dynamic;

import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserProxyFactory {

    private Object target;

    public UserProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        DynamicProxy dynamicProxy = new DynamicProxy(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), dynamicProxy);
    }
}
