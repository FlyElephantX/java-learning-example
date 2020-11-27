package com.example;


import com.example.annotation.Name;
import com.example.annotation.Sex;
import com.example.annotation.User;

import java.lang.reflect.Field;

public class Solution {

    public static String getInfo(Class<?> cs) {
        String result = "";
        //通过反射获取所有声明的字段
        Field[] declaredFields = cs.getDeclaredFields();
        //获取所有字段
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Name.class)) {
                //获取程序元素上的注解
                Name annotation = field.getAnnotation(Name.class);
                String value = annotation.value();
                result += (field.getName() + ":" + value + "\n");
            }
            if (field.isAnnotationPresent(Sex.class)) {
                Sex annotation = field.getAnnotation(Sex.class);
                String value = annotation.gender().name();
                result += (field.getName() + ":" + value);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String info = getInfo(User.class);
        System.out.print(info);
    }

}
