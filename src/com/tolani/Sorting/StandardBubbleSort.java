package com.tolani.Sorting;

import static com.tolani.Sorting.StandardQuickSort.swap;

public class StandardBubbleSort {

    static int[] arr = {10,12,50,100,4,5,44,0,2,77,99,66,22,25};

    public static void main(String[] args)
    {
        BubbleSort(arr,0,arr.length-1);

        StringBuilder sb = new StringBuilder("");

        for(int x : arr)
        {
            sb.append(x);
            sb.append(" ");
        }

        System.out.println(sb);
    }

    public static void BubbleSort(int[] arr , int low , int high)
    {
        for(int i=low ; i < high ; i++)           // high is already arr.len -1 , thts why no need to do -1
        {
            for(int j=low ; j < (high-i+low) ; j++)
            {
                if(arr[j] > arr[j+1])
                {
                    swap(arr,j,j+1);
                }
            }
        }
    }
}
