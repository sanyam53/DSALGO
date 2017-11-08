/*
*   we ve seen this problem in min max section as largest sum subarray
*
*   we cn solve this problem using dp approach in O(n) time
*
*   recusrsive eqn would be :  LSS(1..N) = if LSS(1...N-1) is +ve then we will add arr[n] to it
*                                           and if its -ve then LSS would be simply arr[n] itslf
*
*    LSS(1..N) reprsnts largest contiguous subarray of elemnts 1 to n ( IF WE INCLUDE Nth elemnt)
*
*    so in an array we will store these values and aftr tht we will find max in the array
*
*    now to write this in other way ,
*
*      ""  LSS(n) = Math.max( arr[n] + LSS(N-1) , arr[n] ) ;   ""
*
*       // which is the rec eqn converted to the dp table method , we will store the values and
*       just use it , as simple as tht ,
*
*       t.c is O(n) and s.c is O(1) ! cool
*
* */



package com.tolani.DynamicProgramming;

public class p8_MaximumSumSubArray {

    public static void main(String[] args)
    {
        int[] arr={-2,-3,4,-1,-2,1,5,-3};  // ans : 7

        int[] arr1 = {-2,-3,-4,-5,-1,98};  // ans :98

        int[] arr2 = {1,2,3,-4,5};   // ans shuld be 7

        System.out.println(bottomUpMSS(arr));
    }

    public static int bottomUpMSS(int[] arr)
    {
        int maxVal = arr[0];

        for(int i=1; i < arr.length ; i++)
        {
            arr[i] = Math.max(arr[i] + arr[i-1] , arr[i]);

            if(arr[i] > maxVal) maxVal = arr[i];
        }

        return maxVal;
    }

}
