package com.tolani.Sorting;

import static com.tolani.Sorting.StandardBubbleSort.BubbleSort;
import static com.tolani.Sorting.StandardQuickSort.swap;

public class FindMedian_LinearTime {

    static int[] a = {10,12,50,100,4,5,44,0,2,77,99,66,22,25};

    public static void main(String[] args) {
        int numOfGroups = ceil(a.length, 5);
        int n = numOfGroups;
        int left = 0;
        int right = 4;
        int k;

       // int[] medianArr = new int[numOfGroups];
        int index = 0;

        while (numOfGroups > 0) {
            BubbleSort(a, left, right);            // sorting all 'n/5' groups

            swap(a,index,(left+right)/2);      // swapping median of the sorted list in the beggining of the array
            index++;

            left += 5;
            k = right + 5;

            if (k < a.length - 1) {
                right = k;
            } else {
                right = a.length - 1;
            }

            numOfGroups--;
        }

        DisplayArr(a);    // so u cn see here tht all the medians (specificly n/5) are in d beginning now

        numOfGroups = n;
        BubbleSort(a,0,numOfGroups-1);
        DisplayArr(a);

        int medianOfMedians = a[(0 + numOfGroups -1) /2] ;    // returning median of medians

        System.out.println(medianOfMedians);


    }

    public static void DisplayArr(int[] arr)
        {
            StringBuilder sb = new StringBuilder("");

            for(int x : arr)
             {
                 sb.append(x);
                 sb.append(" ");
                 }

            System.out.println(sb);

         }

    public static int ceil(int a, int b) {

        float f = (float)a/(float)b;
        int i = a/b;

        if(f == i) return i;
        else return i+1;
    }

}


