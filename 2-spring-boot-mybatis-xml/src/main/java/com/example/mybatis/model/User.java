package com.example.mybatis.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public int id;

    public String name;

    public String phone;

    public String telephone;

    public String address;
}
