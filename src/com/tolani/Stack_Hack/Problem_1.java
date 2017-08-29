/*
*   Problem : function B is defined as : for the given array , for each element in the array :
*                                           : Go to the right side of tht element and write the index of
*                                              an element which is lesser than tht (first one u found)
*                                              if no elemnt found whch is lesser then write 0 for it.
*
*               Approach 1 : Brute Force : O(n^2) in the worst case : check all elemnts on the RHS of the given elemnt and store the index as u find
*                                                                       smaller elemnt , and repeat for the next elemnt
*
*               Approach 2 : O(n) (using stack) :   here we will compare the elemnts while pushing , if new elmnt is grtr than the tos then
*                                                   push it
*                                                      else ( if smaller) which we wnt to hppn
*                                                      pop the elemnt (actually index of tht elemnt) from the stck and store the 'i'(currnt pointer)
*                                                      on tht position in the output array
*
**/

package com.tolani.Stack_Hack;

import static com.tolani.Sorting.FindMedian_LinearTime.DisplayArr;

public class Problem_1 {

        static int[] arr = {3,6,8,7,5,2,9,10};

        static int[] arr2 = {1,2,3,4,5,6,7,8,9};

        static int[] arr3 = {9,8,7,6,5,4,3,2,1};

        public static void main(String[] args)
        {
            //Approach1_BruteForce(arr,0,arr.length-1);

           // Approach2_StackHack(arr,0,arr.length-1);

            Approach2_StackHack(arr3,0,arr3.length-1);

            //Approach2_StackHack(arr3,0,arr3.length-1);

        }


        public static void Approach2_StackHack(int[] arr ,int low , int high)
        {
            int[] B_arr = new int[high + 1];     // this arr will store the answer indices , tht we want

            MyStack stck = new MyStack(20);    // creating a stack object with the given size

            int i=0; int index =-1;

            while(i <= high)
            {
                if(stck.isStackEmpty())       // if stack is empty then push the elemnt
                {
                    stck.push(i);
                    i++;
                }

                else if(arr[i] > arr[stck.pick()])     // if elemnt is grtr than top of d stck then also push
                {
                    stck.push(i);
                    i++;
                }

                // note : we can combine above 2 conditions in one if statement

                else if(arr[i] < arr[stck.pick()])       // pick() method will return the contents of the top of the stack
                {
                    index = stck.pop();
                    B_arr[index] = i;        // so as we found the smaller element we will store the index of smaller elemtn whr our 'i' is pointing now
                    continue;                // in the popped index position : bcz it is a position whr our elemnt is stored in actual arr
                }
            }

            while(!stck.isStackEmpty())
            {
                index = stck.pop();          // we r popping indices of elemnts left into the arr
                B_arr[index] = 0;            // now store 0 at these locatins bcz thr is no elemnt smaller than them on theier right side
            }

            DisplayArr(B_arr);
        }
}
