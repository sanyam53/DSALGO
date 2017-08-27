/*

    Problem : given a list ( A1,A2,...An)  ,  find  Max(Aj - Ai) , whr  j >= (i + dist.)

    real world analogy : bought stock on ith day and sold it on jth day but diff bw these two shuld be
                         at least 'dist. #of days' (whr dist is a var reprsnts min dist bw i and j)

    same as problem no. 5 , only one change need to be done whr u ve to keep track of minimum element
    in the range of " i to (j - dist.)" , in other words 'j-dist' #of elements left to the 'j' wont be
    considerd

 */



package com.tolani.MaxMinProblems;

public class Problem_6 {

    static int[] arr = {5,3,10,15,4,0,6,12,18,8,9,13,2};

    static int[] arr2 ={20,10,5,40,80};     // see this test case : more intuitive

    public static void main(String[] args)
    {
        int ans;
        ans = Approach2(arr,0,arr.length-1,2);
        System.out.println(ans);

         ans = Approach2(arr2,0,arr2.length-1,2);
         System.out.println(ans);

         ans = Approach2(arr2,0,arr2.length-1,3);
         System.out.println(ans);

    }

    public static int Approach2(int[] arr , int low , int high, int dist)
    {
        int iMin = 0;

        int j = iMin + dist;         // bcz j > i + dist

        int maxdiff = arr[j] - arr[iMin];

        for(int k = j+1 ; k <= high ; k++)
        {
            if(arr[k-dist] < arr[iMin]) { iMin = k-dist;}

            int var = arr[k] -arr[iMin];

            if(var > maxdiff) {maxdiff = var;}
        }

        return maxdiff;

    }

}
