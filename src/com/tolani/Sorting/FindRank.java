//Problem: Rank is given to us so now, Find the element a[i] in the given array ( which we impn below )

    //    or it can be formed as find the rank of the given element a[i] of an array

// approach 1 can be brute force : sort the list and just return the (high-indexOfElement+1)
// approach 2 : lets see

package com.tolani.Sorting;
import static com.tolani.Sorting.StandardQuickSort.ChoosePivotRandomly;
import static com.tolani.Sorting.StandardQuickSort.partition;

public class FindRank {

    static int[] arr= {1,20,4,5,55,33,22,11,77,0};

/*
index    : 0  1  2  3  4   5   6   7   8  9
elements : 0  1  4  5  11  20  22 33  55  77
rank     : 10 9  8  7   6   5   4  3   2   1
*/

public static void main(String[] args)
    {
        int ans = FindRankMethod(arr,0,arr.length-1,6);
        System.out.println(ans);
    }

    private static int FindRankMethod(int[] arr, int low, int high, int rank) {

        int pivotIndexChosen = ChoosePivotRandomly(low,high);       // we ve choose a pivot

        int pivotIndex = partition(arr,low,high,pivotIndexChosen);   // now pivot get settled by partition method

        if(rank == (high-pivotIndex+1))         // bcz rank is #of people ahead of u +1
        {
            return arr[pivotIndex];      // we r returng an element value of this index *tc*
        }

        else if (rank < (high-pivotIndex+1)) return FindRankMethod(arr,pivotIndex+1,high,rank);
        // if rank is better than this pivot elemnt we will recur on right side of that pivot

        else     // rank is worst than the pivot we got , then we will recur on the left side of tht pivot
        {
            return FindRankMethod(arr,low,pivotIndex-1,rank-(high-pivotIndex+1));

 // beautiful logic here :  whn u recur to d right side means to d better ranks so rank wont get affectd
 // whn u rcur on d left half list , means sm ppl have better rank than them , so if u call fresh recursv
 // functin on tht list then u ve to discard the ppl with bettr ranks from the rank u wnt to find.
 // so we discarded whole right list which r hvng bettr rank , to make our recursve fun run correctly

        }



    }
}
