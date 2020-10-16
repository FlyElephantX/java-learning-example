package com.example.annotation;

public class User {

    @Name("elephant")
    public String name;
    public String age;
    @Sex(gender = Sex.GenderType.Male)
    public String sex;
}
