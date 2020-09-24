package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
//        User user = new User();
//        user.setName("Elephant");
//        user.test();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        User user = (User)applicationContext.getBean("user");
        user.test();
    }
}
