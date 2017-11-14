/*
*
*   Problem : Given an array and a value, remove all instances of that value in place and return the new length.
                Do not allocate extra space for another array, you must do this in place with constant memory.
                The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*
*       ex : arr = { 3,2,2,3 }  : ans here is : {2,2,..}  and len = 2
*
*       do it in O(n) time ! think !
*
*       logic : slow pointer and fast pointer logic wokrs here : how ? : > slow pointer will point to '3' means given value only
*                                                                           and fast pointer will skim towards the next non-3 (non val element)
*                                                                           then we will swap them
*
*      at max 'n' swaps can happen : if '3' appears at the beginnnin , bcz it will always get swapped then
*      so it is O(n)
*
* */


package com.tolani.LeetCode.Arrays.Easy;

import static com.tolani.Sorting.Deterministic_Pivot.displayArr;

public class p3_RemoveElement {

    static int[] arr = {1,2,3,3,3,3,4,6,9,5,6,3,3,2};

    public static void main(String[] args)
    {
        int ans = removeElement(arr,3);
        displayArr(arr);
        System.out.println("new length is : " + ans);
    }

    public static int removeElement(int[] nums, int value) {

        int slow =0;

        while( slow < nums.length && nums[slow] != value) { slow++; }      // pushing slow pointer on the given value first
                                                                        // if we dnt do this then unnecesary swaps of two distinct vals will happen
        for(int fast = slow + 1 ; fast < nums.length ; fast++)
        {
            if(nums[slow] != nums[fast] && nums[slow] == value)
            {
                int temp = nums[slow];           // swapping 3 with non 3 element
                nums[slow] = nums[fast];
                nums[fast] = temp;

                slow++;
            }

            // if both slow and fast pointers are equal means it is a window of '3' element
        }

        return (slow);
    }
}
