//Problem: Find the element a[i] in the given array of given rank

    //    or it can be formed as find the rank of the given element a[i] of an array

// approach 1 can be brute force : sort the list and just return the (high-indexOfElement+1)
// approach 2 : lets see

package com.tolani.Sorting;
import static com.tolani.Sorting.StandardQuickSort.ChoosePivotRandomly;
import static com.tolani.Sorting.StandardQuickSort.partition;

public class FindRank {

    static int[] arr= {1,20,4,5,55,33,22,11,77,0};

    public static void main(String[] args)
    {
        int ans = FindRankMethod(arr,0,arr.length-1,6);
        System.out.println(ans);
    }

    private static int FindRankMethod(int[] arr, int low, int high, int rank) {

        int pivotIndex = ChoosePivotRandomly(low,high);

        partition(arr,low,high,pivotIndex);

        return 1;
    }
}
