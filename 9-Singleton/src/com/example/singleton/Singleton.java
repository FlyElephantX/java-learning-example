package com.example.singleton;

public class Singleton {

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        Singleton3 singleton3 = Singleton3.getInstance();
        System.out.println("name1:" + singleton1.name);
        System.out.println("name2:" + singleton2.name);
        System.out.println("name3:" + singleton3.name);
    }
}
