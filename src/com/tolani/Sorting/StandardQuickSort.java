/*
    1. standard quick sort : choose low as pivot elemnt and find smaller elements than pivot & put them left of
                            the pivot , bigger elements on d right side of d pivot , so pivot is settled ok

        Problem is : if list is sorted or reversly sorted then partion would be 1 : n-1 , whch is O(n^2)
        Solution is : randomized quick sort

    2. Randomized quick sort : so in it we choose pivot randomly and not as low element.
                               how this will help ? : suppose list is sorted , so in this pivot choosen
                               randomly so partion wont be 1 : n-1 but something else , mostly avg partitn
         so , randomized quick sort is not dependent on input ordering , yes thr is a pssblity of 1 : n-1
         partition but thts unlucky pivot in tht run only.

         so it is O(n*logn) in all of the cases (other way of impn is u can shuffle the given ip list)

    if we want to partition in exactly two halves > n/2 : n/2 then find median and take it as pivot

 */


// to partition our sequence in exactly two parts n/2 : n/2 , we can replace our RandomPivotFun with
// Median Finding Function : median is actually the mid element value not an index value
// finding median will take O(n*logn) time if u sort and return the median index
// but we can find median in a linear Time. lets see how :

package com.tolani.Sorting;

import java.util.Random;

public class StandardQuickSort {

    static int[] arr = {10,20,11,21,5,2,30,25,6,51,41,45,0};

    public static void main(String[] args)
    {
        QuickSort(arr,0,(arr.length-1));

        StringBuilder sb = new StringBuilder("");

        for(int x : arr)
        {
            sb.append(x);
            sb.append(" ");
        }

        System.out.println(sb);
    }

    public static void QuickSort(int[] arr,int low,int high)
    {
        int pivotIndex;

        if(low < high) {

            int chosePivot;
            
            //chosePivot = ChooseLowAsPivot(low,high);    // choosing the first element as a pivot

            chosePivot = ChoosePivotRandomly(low,high);     // index of pivot we are choosing
            // to choose pivot randomly (uniformly)

            pivotIndex = partition(arr, low, high, chosePivot);       // index whr pivot get settled

            QuickSort(arr, low, pivotIndex - 1);
            QuickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr,int low,int high,int pivot)
    {

        swap(arr,low,pivot);       // swapping pivot element with low element to make our this code work
        pivot = low;      // we swapped pivot elemnt with low elemnt so now change the index to make our code run


        int i= low+1;    // 'i' is next to the pivot element
        int j= high;

        while(i <= j) {    // tc : if we dont put = here then at last it wont be called whn thr r only two elements left

  //tc:   // note : i <= high is imp here man ! so thts why arrindexoutofbounds ws coming

            while (i <= high && arr[i] < arr[pivot])      // finding bigger element frm the left side
            {
                i++;
            }

            while (j >= low && arr[j] > arr[pivot])     // finding smaller element frm the right side
            {
                j--;
            }

            // bcz we want to put smaller elements on d left of pivot and bigger elemnts on d right of pivot

            //tc :        // swap(arr[low],arr[high]); : this is pass by value this wont affect an actual array
            if (i < j)      // this swap ll be done only if i < j : debug it and know it man ! hint : at last iteratin
            {
                swap(arr, i, j); // here bcz array is an object in java, so it is pass by reference here
            }
        }

        swap(arr,pivot,j); // think about this : tht why we put 'j' here , bcz j will cross i in final step of above iteratin

        return j;       // returning an index where pivot get settled
    }

    private static int ChooseLowAsPivot(int low, int high) {
        return low;
    }

    public static void swap(int[] arr , int a , int b)
    {
        int element = arr[a];
        arr[a] = arr[b];
        arr[b] = element ;
    }

    static Random r = new Random();

    public static int ChoosePivotRandomly(int low,int high)
    {
        return (low + (r.nextInt(1000) % (high-low+1)));

        // this will generate a random num uniformly
        // suppose low is 2 and high is 5 then , a rand no genrated and ans wuld be bw 0 1 2 3 bcz range is
        // now +2(+low) will be done at last
    }

}
