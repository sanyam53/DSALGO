package com.tolani.HashTable;

public class Test_MyHashTable {

    public static void main(String[] args)
    {
        MyHashTable ht = new MyHashTable();

        ht.add(1,234);
        ht.add(2,444);
        ht.add(20,456);
        ht.add(29,999);
        ht.add(4,888);
        ht.add(78,765);

        ht.displayHashTable();

        ht.add(1,233);      // try with same key man
        ht.add(40,345);

        System.out.println("-------------------------------------------------------------------" + '\n');

        ht.displayHashTable();

        ht.add(30,444);
        ht.add(50,455);
        ht.displayHashTable();

        ht.remove(30);
        ht.displayHashTable();

        ht.remove(20);
        ht.remove(40);
        ht.remove(50);
        ht.remove(50);       // linked list is empty now and we r removing
        ht.remove(11);        // thr is no such key 11 in the list :p
        ht.displayHashTable();
    }
}
