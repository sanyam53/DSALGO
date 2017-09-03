/*
*       standard TWO SUM Problem : Given an array of integers , find two indices such that (Ai + Aj == x ) ; where  i != j
*
*       Approach 1 : (Brute Force) : O(n^2) : two loops : first will take one element 'i' and scnd will search for (x - Ai) in the list
*                                                               so for n elements it will tk qudratic time
*
*       Approach 2 : (sorting) : O(nlogn) : step 1 : first sort the list
*                                           step 2 : two pointer approach : l & r : if(sum of l & r == x) then return
*                                                                                   else if (sum is smaller) then l++;
*                                                                                   else r--;
*
*       Approach 3 : (hashing) : O(2*n) : O(n) : as u cn see in the brute force approach we r doing SEARCH operation , now search operatin is O(1) for
*                                                 hash table :: so now think !
*
*                 O(n) space complexity >take a hash table of the size of the array (note : hash table cn work only if size of arr is known ? )
*                                       >store all the values of the array as a KEY of h.t. and indices as the VALUE of h.t.  : O(n) for this
*                                       >now once u stored all the values , now search for the complement value : means ( x - a[i] ) in hash table
*                                           , so now this search will tk constant time for each element : so it will tk linear time in total
*                                       >so total O(2n) time.
*
*                                       here on succesful serach we will return an index which is VALUE of the KEY
* */






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
