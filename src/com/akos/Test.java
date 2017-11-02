package com.akos;

public class Test {
    public static void main(String[] args) {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        System.out.println(array);
        array.add(3);
        array.add(5);
        array.add(-38);
        System.out.println(array);
    }
}
