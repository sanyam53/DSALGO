
/*
*     Problem : given a sorted array , remove the duplicates and return the array with distinct elements with its length
*
*       constraint : u ve to do it without using any extra space : means in constant space
*        note : it doesnt matter wt u leave out in the array beyond the new length
*
*       ex : Arr :  1 1 1 1 2 3 4 5 5 6 7  7  7  8
*           index:  0 1 2 3 4 5 6 7 8 9 10 11 12 13
*            ans :  1 2 3 4 5 6 7 8  & length = 8;
*
*
*           logic here is to do it in O(n) time ! think !
*
*           > take two pointer 'slow pointer' and 'fast pointer'
*           > target the window of duplicate elements : move fast pointers at d end of the window
*           > now incrmnt slow pointer by 1 and copy the next distinct element frm the fast pointer which is aftr the window
*
*           suppse 'i' is pointing to index 0 : now fast ptr 'j' will go till index 4 means passed the win
*           of duplicate elements : now incrmnt the 'i' pointer and copy tht distinct elemnt thr , and repeat this and return i+1 at last to return length of the new array
* */

package com.tolani.LeetCode.Arrays.Easy;

import static com.tolani.Sorting.FindMedian_LinearTime.displayArr;

public class p2_RemoveDuplicatesFromSortedArray {

    static int[] arr = {1,1,1,1,2,3,4,5,5,5,6,7,8,8,9,10};

    public static void main(String[] args)
    {
        int slow =0;

        for(int fast = 1; fast < arr.length; fast++)
        {
            if(arr[slow] != arr[fast])
            {
                slow++;   // moving slow pointer by 1 so tht we cn copy the next distinct element there
                arr[slow] = arr[fast];
            }

            // j++ every time
        }

        displayArr(arr);

        System.out.println("length is " + (slow+1));

        // note : it doesnt matter what you leave beyond the new length : means thr cn be elemnets in the
        // array after the new length. which r irrelevent

    }
}
