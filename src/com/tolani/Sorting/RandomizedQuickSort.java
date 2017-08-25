package com.tolani.Sorting;

import static com.tolani.Sorting.StandardQuickSort.*;  // static import is used to use static members define in that class

public class RandomizedQuickSort {

    static int[] sortedArr = {10,9,8,7,6,5,4,3,2,1,0};

    public static void main(String[] args)
    {
        QuickSort(sortedArr,0,sortedArr.length-1);

        StringBuilder sb = new StringBuilder("");

        for(int x : sortedArr)
        {
            sb.append(x);
            sb.append(" ");
        }

        System.out.println(sb);


        // to check the randomized quick sort power , put the watch point on last swap in partiton and see how partition happens while debugging. cool !
    }

}
