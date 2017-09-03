package com.tolani;

public class TestAnything {

    public static void main(String[] args)
    {
        int key = 10;

        Integer i = key;

        Integer j = key;

        System.out.println(i.hashCode());
        System.out.println(j.hashCode());
    }

}
