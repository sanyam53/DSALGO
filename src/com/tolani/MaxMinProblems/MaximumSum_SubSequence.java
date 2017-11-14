 /*
 *    Problem : Maximum sum Subsequence possible for the given list of elements : A0,A1,....An
 *              subseqn here is contiguous chunk of elements : we ve to find all give the subseqn which totals to the maximum
 *
 *              App.1 : (brute force) : 3 loops : first loop for starting point of subseqn , scnd loop for the ending point of subseqn
 *                                                  3rd loop to count the sum of tht range and comapre it and give max
 *                                                  : this will take O(n^3) , not so efficient
 *
 *              App.2 : (optimized brute force) : 2 loops : in this we will do the sum in the 2nd loop itself and compare with previous sum thts it
 *                                                  : O(n^2) : bit optimized but not efficient
 *
 *              App.3 : ( Linear Time) : ( Prefix Sum Approach ) : we will take an extra array and we store the prefix sum in that array : O(n) for this
 *
 *                                                                 then we will find "Max(Aj - Ai)" where j > i  : whch will take O(n) time
 *                                                                  and we will return the maxdiff. which is a max sum pssble in subseqn ! think !
 * */

package com.tolani.MaxMinProblems;

 import static com.tolani.Sorting.Deterministic_Pivot.displayArr;

 public class MaximumSum_SubSequence {

    static int[] arr = {-2,-3,-4,-5,-1,98};

    static int[] arr2 = {1,2,3,-4,5};   // ans shuld be 7

    public static void main(String[] args)
    {
       int ans = Approach1(arr,0,arr.length-1);
       System.out.println(ans);

        ans = Approach2(arr,0,arr.length-1);
        System.out.println(ans);

       ans= Approach3(arr,0,arr.length-1);
        System.out.println(ans);
    }

    public static int Approach1(int[] arr , int low , int high)
    {
        int max = Integer.MIN_VALUE;
        int sum;

        for(int i=0 ; i <= high ; i++)        // 'i' cant be == last index or high index bcz if u tk min len of subseqn 2 then if 'i' points to d
        {                                           // last index then 'j' loop wont run and only last element will be counted as subseqn
            for(int j=i ; j <= high ; j++)       // if subseqn length can be 1 then j=i , else min subseqn is of length 2 then j shuld be i+1
            {
                sum =0;

                for(int k= i ; k <= j ; k++)
                {
                    sum = sum + arr[k];
                }

                if(sum > max){max = sum;}
            }
        }
        return max;
    }

    public static int Approach2(int[] arr , int low , int high)
    {
        int sum=0;
        int max = Integer.MIN_VALUE;

        for(int i=0 ; i <= high ; i++)
        {
            sum = arr[i];

            for(int j=i+1 ; j <= high ; j++)
            {
               sum = sum + arr[j];
               if(sum > max){ max = sum;}
            }
        }

        return sum;
    }

    public static int Approach3(int[] arr , int low , int high)
    {
        int maxInPrefixArr =0;

        int[] prefixArr = new int[( high + 1 ) + 1];

        prefixArr[0] = 0;           // putting first element as 0 in prefix array
        prefixArr[1] = arr[0];

        for(int i=2 ; i <=high+1 ; i++)
        {
            prefixArr[i] =  prefixArr[i-1] + arr[i-1];     // constructing prefix array
        }

        displayArr(prefixArr);

        int ans = Problem_5.Approach2(prefixArr,0,prefixArr.length-1);

        return ans;

    }

}
