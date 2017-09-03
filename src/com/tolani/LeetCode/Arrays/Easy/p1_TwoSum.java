package com.tolani.LeetCode.Arrays.Easy;

import com.tolani.HashTable.MyHashTable;

public class p1_TwoSum {

    static int[] arr = {1,5,6,78,55,44,33,22,99,100,140};

    static int x = 35;       // x shuld be greater than the array element

    public static void main(String[] args)
    {
       MyHashTable ht = new MyHashTable(arr.length);     // creating the hash table with the size of the given array

       for(int i=0 ; i < arr.length ; i++)
       {
           ht.add(arr[i],i);       // (KEY) will be the value of the array elemnts and (VALUE) wuld be the index of those elements
       }

       boolean b = false;

       for(int j =0 ; j < arr.length ; j++)
       {
           int index = -1;
           if(x > arr[j]) {           // this condition is very imp bcz if elemnt in arr is grtr thn 'x' then no sense in checking Ai + Aj == x
               index = ht.search(x - arr[j]);
           }
           if(index != -1)
           {
               System.out.println(j + " " + index);
               b = true;
               break;
           }
       }

       if(!b){System.out.println("no such elements exist in the array");}
    }
}
