/*
    Problem : given a list ( A1,A2,...An)  ,  find  Max(Aj - Ai) , whr  j > i

    real world analogy is : stock market , u buy a stock on day 'i' and u sell it on day 'j'
                            so u wnt to find the max diff. bw pricing from any later day (j)
                            to any previous day (i)

    Approach 1 ( Brute Force ) : put two loops and  j -> i+1 to n
            O(n^2)

    Approach 2 ( optimized ) : Logic here is that u wnt to find the max difference ultimately
                               and we know that i < j , so we will keep track of minimum on the left
         O(n)                  side of 'j' , so we can subtract tht minimum from the 'j' and it will
                               give max diff pssble

                               ex :   1 2 3 4 5 , suppose 'j' is 5 then max diff for  i < j is
                                                  maxdiff = { a[j] - min on the left side list of 'j' }

 */


package com.tolani.MaxMinProblems;

public class Problem_5 {

    static int[] arr = {5,3,10,15,4,0,6,12,18,8,9,13,2};

    static int[] arr2 = {5,7,17,-2,100};

    public static void main(String[] args)
    {
        int ans = Approach2(arr2,0,arr2.length-1);
        System.out.println(ans);
    }

    public static int Approach2(int[] arr,int low ,int high)
    {
        int iMin=0;   // this is minimum
        int j=1;

        int maxdiff = arr[j] - arr[iMin];

        for(int k= 1 ; k < high ; k++)
        {
            j++;

            if(arr[k] < arr[iMin]) { iMin = k;}    // changing min

            int var = arr[j]-arr[iMin];        // doing Aj - Amin

            if(var > maxdiff){maxdiff = var;}
        }

        return maxdiff;
    }
}
