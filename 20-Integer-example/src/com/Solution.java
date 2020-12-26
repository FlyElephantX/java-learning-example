package com;

public class Solution {

    public static void main(String[] args) {
        Integer a;
        Integer b = 200;
        a = b + 1;
        System.out.println("a:" + a);
        Integer i1 = new Integer(10);
        Integer i2 = new Integer(10);
        System.out.println(i1 == i2); //false
        Integer i3 = new Integer(10);
        int i4 = 10;
        System.out.println(i3 == i4); //true
        Integer i5 = new Integer(10);
        Integer i6 = 10;
        System.out.println(i5 == i6); //false

        Board board = new Board();
        Integer res;
        res = board.getCount() + 1;
        System.out.printf("结果:" + res);
    }
}
