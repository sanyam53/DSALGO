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
*   // note : we took LSS(1..n-1) here in req eqn ? why ? : bcz subarray need to be contiguous
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

    public static int bottomUpMSS(int[] MSS)
    {
        int maxVal = MSS[0];

        for(int i=1; i < MSS.length ; i++)
        {
            // max sum subarray at index 'j' (if we include j) will be calculated by following eqn

            // MSS(1..j) = Max( Vj + MSS(j-1) , Vj )

            // means MSS at 'j' is maxmum of ( MSS already calucltd till 'j-1' , or Value(j) )
            // in LIS prolem we wr considering all i values tht r less thn 'j' , but as this is the contiguous chunk of elements we ve to only
            // consider the 'j-1'th index ONLY

            MSS[i] = Math.max(MSS[i] + MSS[i-1] , MSS[i]);

            if(MSS[i] > maxVal) maxVal = MSS[i];
        }

        return maxVal;
    }

}
