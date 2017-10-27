/*
*   Problem : count the force of the given array
*
*   force is the addition of squared value of occurence of the values in the array
*
*   soln 1 : as gvn arr is nt ordered u sort it then count the occurence and calculate force : O(nlogn)
*
*   soln 2 : u count the occurence in a hashtable , then calcualte force : O(n) ! cool .
*
* */



package com.tolani.hackerearth;

import java.util.*;

public class problem1 {

    public static void main(String[] args) {
        int[] a = {1, 0, 1, 1};

       int ans = countForce(a);

        System.out.println(ans);
    }

    public static int countForce(int[] a) {
        Map<Integer, Integer> ht = new HashMap<>();    // key = val of arr element , val = count of it or occurences of the val

        for (int i = 0; i < a.length ; i++) {
            if (ht.containsKey(a[i])) {
                ht.put(a[i], ht.get(a[i]) + 1);
            } else {
                ht.put(a[i], 1);
            }
        }

        //System.out.println(ht);

        // lets calculate the force : which is addition of squared values of occurence of each number in the given array

        int force =0;

        Collection c = ht.values();
        System.out.println(c);

        Iterator i = c.iterator();

        int val=0;

        while(i.hasNext())
        {
            val = (Integer)i.next();       // iterator.next() will return an object so we ve to type case
            force = force + (val*val);
        }

        return force;
    }
}