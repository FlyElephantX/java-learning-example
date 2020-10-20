package com;

public class Solution {

    public static void main(String[] args) {
        PasswordUtil util = new PasswordUtil();
        String password = util.generate("elephant");
        System.out.println("加密过后的字符串:" + password);
        System.out.println(util.verify("elephant", password));
    }
}
