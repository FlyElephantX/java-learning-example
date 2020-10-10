package com.example.singleton;

public class Singleton3 {

    public String name = "elephant3";

    private Singleton3() { }

    public static class Holder {
        public static Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return Holder.instance;
    }
}
