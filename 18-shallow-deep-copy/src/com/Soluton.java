package com;

public class Soluton {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = str1;
        System.out.println("str1:" + str1 + "---str2:" + str2);
        str1 = "abcd";
        System.out.println("str1:" + str1 + "---str2:" + str2);

        String str3 = new String("123");
        String str4 = str3;
        System.out.println("str3:" + str3 + "---str4:" + str4);
        str3 = "1234";
        System.out.println("str3:" + str3 + "---str4:" + str4);
    }
}
